package com.example.amazinglu.in_app_search_demo;

import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * to implements App specific suggestion -> use custom Content provider
 * base idea:
 * content provider query the suggestion database and return the result to searchable activity using intent
 *
 * 1. define the content provider
 * we need to let the system knows which provider is used for generate search suggestion and
 * which intent is used to send the suggestion back to searchable activity
 *
 * see "suggest" related definition in searchable.xml and provider xml in Manifest
 * the authorities see be the same in searchable.xml and Manifest
 *
 * 2. get the origin query the user input
 * the query is sent in a uri form to the content provider, which is:
 * content://your.authority/optional.suggest.path/SUGGEST_URI_PATH_QUERY
 * authority: the authority we define for the content provider
 * optional.suggest.path: the string we define in android:searchSuggestPath in searchable.xml
 * to get the origin query:
 * String query = uri.getLastPathSegment().toLowerCase();
 *
 * search and return the suggestion strings
 * search is the place we can DIY
 * build the suggestion strings:
 * must format the suggestions as rows in a table and present them with a Cursor. Need to use specific
 * columns that the system can understand
 *
 * 3. searchable activity do sth when user click the suggestion
 * we can use SUGGEST_COLUMN_INTENT_DATA, SUGGEST_COLUMN_INTENT_DATA_ID and SUGGEST_COLUMN_INTENT_EXTRA_DATA
 * to return send data from content provider to searchable activity
 * */

// TODO: custom suggestion with SQLite database
public class MyCustomSuggestionProvider extends ContentProvider {

    private static final String STORES = "stores/"+ SearchManager.SUGGEST_URI_PATH_QUERY+"/*";

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI("com.example.amazinglu.in_app_search.MyCustomSuggestionProvider", STORES, 1);
    }

    private static String[] matrixCursorColumns = {"_id",
            SearchManager.SUGGEST_COLUMN_TEXT_1,
            SearchManager.SUGGEST_COLUMN_ICON_1,
            SearchManager.SUGGEST_COLUMN_INTENT_DATA,
            SearchManager.SUGGEST_COLUMN_INTENT_DATA_ID};


    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        switch (uriMatcher.match(uri)) {
            case 1:
                String query = uri.getLastPathSegment().toLowerCase();
                return getSearchResultsCursor(query);
            default:
                return null;
        }
    }

    private MatrixCursor getSearchResultsCursor(String query) {
        MatrixCursor searchResult = new MatrixCursor(matrixCursorColumns);
        Object[] mRow = new Object[5];
        int countId  = 0;
        if (query != null) {
            /**
             * search for suggestion string base on the origin query
             * */
            for (String element : StoreData.getStores()) {
                if (element.toLowerCase().contains(query)) {
                    mRow[0] = "" + countId;
                    mRow[1] = element;
                    mRow[2] = getContext().getResources().getDrawable(R.drawable.ic_launcher_background);
                    mRow[3] = element;
                    mRow[4] = "" + countId;
                    countId++;

                    searchResult.addRow(mRow);
                }
            }
        }
        return searchResult;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}

package com.example.amazinglu.in_app_search_demo;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SearchEvent;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menus_search, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final android.support.v7.widget.SearchView searchView =
                (android.support.v7.widget.SearchView) menu.findItem(R.id.action_search).getActionView();

        // Assumes current activity is the searchable activity
        /**
         * 如果用search widget的话
         * menus中的item记得加上 app:actionViewClass="android.support.v7.widget.SearchView"
         * 然后如果有额外的数据要传递到searchable activity
         * 用 searchView.setAppSearchData(appData) 将Bundle搞进去
         * */
        fakeData();
        Bundle appData = new Bundle();
        appData.putStringArrayList(SearchableActivity.KEY_DATA, (ArrayList<String>) data);
        searchView.setAppSearchData(appData);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true); // Do not iconify the widget; expand it by default
        // show the summit button
        searchView.setSubmitButtonEnabled(true);
        // 可以把suggest 加入到 query中
        searchView.setQueryRefinementEnabled(true);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSearchRequested() {
        fakeData();
        Bundle appData = new Bundle();
        appData.putStringArrayList(SearchableActivity.KEY_DATA, (ArrayList<String>) data);
        startSearch(null, false, appData, false);
        return true;
    }

    private void fakeData() {
        data = new ArrayList<>();
        for (int i = 0 ; i < 10; ++i) {
            data.add("Search" + i);
        }
    }
}

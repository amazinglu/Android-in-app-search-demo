package com.example.amazinglu.in_app_search_demo.search_suggestion;

import android.content.SearchRecentSuggestionsProvider;

/**
 * adding recent query suggestion
 * step by step:
 * https://developer.android.com/guide/topics/search/adding-recent-query-suggestions
 * */
public class RecentSuggestionProvider extends SearchRecentSuggestionsProvider {

    public static final String AUTHRITY =
            "com.example.amazinglu.in_app_search_demo.search_suggestion.RecentSuggestionProvider";
    public static final int MODE = DATABASE_MODE_QUERIES | DATABASE_MODE_2LINES;

    public RecentSuggestionProvider() {
        setupSuggestions(AUTHRITY, MODE);
    }
}

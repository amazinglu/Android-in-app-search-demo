package com.example.amazinglu.in_app_search_demo.search_suggestion;

import android.content.SearchRecentSuggestionsProvider;

public class RecentSuggestionProvider extends SearchRecentSuggestionsProvider {

    public static final String AUTHRITY =
            "com.example.amazinglu.in_app_search_demo.search_suggestion.RecentSuggestionProvider";
    public static final int MODE = DATABASE_MODE_QUERIES | DATABASE_MODE_2LINES;

    public RecentSuggestionProvider() {
        setupSuggestions(AUTHRITY, MODE);
    }
}

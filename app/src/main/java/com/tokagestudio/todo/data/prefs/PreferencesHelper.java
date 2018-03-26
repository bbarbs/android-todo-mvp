package com.tokagestudio.todo.data.prefs;

import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * Utility for storing data in {@link android.content.SharedPreferences}
 * <p>
 * Created by bbarbero on 3/25/2018.
 */

public class PreferencesHelper {

    private SharedPreferences preferences;

    @Inject
    public PreferencesHelper(SharedPreferences preferences) {
        this.preferences = preferences;
    }
}

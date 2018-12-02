package com.example.woonho.bitflowsimulator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {
    private static final String APP_PREF_NAME = "BitFlowSimulator";

    private static PreferenceHelper preferenceHelper = null;
    private static SharedPreferences prefs;
    private static SharedPreferences.Editor editor;

    private PreferenceHelper() {}

    @SuppressLint("CommitPrefEdits")
    public static PreferenceHelper getInstance(Context context) {

        if (preferenceHelper == null) {
            preferenceHelper = new PreferenceHelper();
        }

        if (prefs == null) {
            prefs = context.getSharedPreferences(APP_PREF_NAME, Context.MODE_PRIVATE);
            editor = prefs.edit();
        }
        return preferenceHelper;
    }

    public void putIntExtra(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public int getIntExtra(String key) {
        return prefs.getInt(key, 0);
    }

    public void putStringExtra(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public String getStringExtra(String key) {
        return prefs.getString(key, "");
    }

    public void putLongExtra(String key, long value) {
        editor.putLong(key, value);
        editor.commit();
    }

    public long getLongExtra(String key) {
        return prefs.getLong(key, 0);
    }

    public void putBooleanExtra(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public boolean getBooleanExtra(String key) {
        return prefs.getBoolean(key, false);
    }
}

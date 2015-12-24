package com.kritsin.tempapp1.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;


public class AppConfig {
    public static final String API_ENDPOINT = "http://atw-api.azurewebsites.net/api/";

    private static SharedPreferences sPrefs;
    private static Context sContext;

    private static final String SELECTED_CITY="SelectedCity", CONFIG="config";

    public static void init(Context context) {
        sContext = context;
        sPrefs = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
    }

    public static String getSelectedCity() {
        return sPrefs.getString(SELECTED_CITY, "");
    }

    public static void setSelectedCity(String name) {
        sPrefs.edit().putString(SELECTED_CITY, name).apply();
    }

}

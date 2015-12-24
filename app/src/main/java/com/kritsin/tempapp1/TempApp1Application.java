package com.kritsin.tempapp1;

import android.app.Application;

import com.kritsin.tempapp1.util.AppConfig;


public class TempApp1Application extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppConfig.init(this);
    }
}

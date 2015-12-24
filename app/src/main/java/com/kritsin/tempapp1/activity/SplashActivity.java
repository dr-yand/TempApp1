package com.kritsin.tempapp1.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kritsin.tempapp1.R;
import com.kritsin.tempapp1.util.AppConfig;

import java.util.concurrent.TimeUnit;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(AppConfig.getSelectedCity().equals("")){
                    startActivity(new Intent(SplashActivity.this, CitiesActivity.class));
                }
                else {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }
                finish();
            }
        }).start();
    }

}

package com.kritsin.tempapp1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.JsonElement;
import com.kritsin.tempapp1.R;
import com.kritsin.tempapp1.adapter.CitiesAdapter;
import com.kritsin.tempapp1.api.ApiManager;
import com.kritsin.tempapp1.model.City;
import com.kritsin.tempapp1.util.AppConfig;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CitiesActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView mCityList;
    private Button mSelectCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);

        mSelectCity = (Button)findViewById(R.id.select_city);
        mSelectCity.setOnClickListener(this);
        mCityList = (ListView)findViewById(R.id.city_list);
        mCityList.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.select_city){
            getCities();
        }
    }

    void getCities() {
        ApiManager.getInstance().withCallback(this, new Callback<JsonElement>() {
            @Override
            public void success(JsonElement element, Response response) {
                try {
                    List<City> tariffs = City.parseJson(element.getAsJsonObject().getAsJsonArray("Result"));
                    fillCityList(tariffs);
                } catch (Exception error) {
                    showError(error);
                }
            }

            @Override
            public void failure(final RetrofitError error) {
                error.printStackTrace();
                showError(error);
            }

            private void showError(final Throwable error) {
                CitiesActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CitiesActivity.this);
                        alertDialogBuilder.setMessage("Произошла ошибка (" + error.toString() + ")");
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    }
                });
            }
        }).getCities();
    }

    private void fillCityList(List<City> cities){
        CitiesAdapter coinsAdapter = new CitiesAdapter(this, cities);
        mCityList.setAdapter(coinsAdapter);
        mSelectCity.setVisibility(View.GONE);
        mCityList.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        City item = (City)parent.getItemAtPosition(position);
        AppConfig.setSelectedCity(item.getName());
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}

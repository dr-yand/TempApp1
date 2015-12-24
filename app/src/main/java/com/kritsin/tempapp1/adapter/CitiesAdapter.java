package com.kritsin.tempapp1.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kritsin.tempapp1.model.City;

import java.util.List;


public class CitiesAdapter extends ArrayAdapter<City>{

    public CitiesAdapter(Context context, List<City> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    public CitiesAdapter(Context context, City[] objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        return view;
    }
}

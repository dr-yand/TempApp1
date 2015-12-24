package com.kritsin.tempapp1.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class City {
    private long id;
    private String name, imageLink;

    public City(){

    }

    public City(JsonObject json) {
        if(!json.get("Id").isJsonNull())
            id = json.get("Id").getAsLong();
        if(!json.get("Name").isJsonNull())
            name = json.get("Name").getAsString();
        if(!json.get("ImageLink").isJsonNull())
            imageLink = json.get("ImageLink").getAsString();
    }


    public static List<City> parseJson(JsonArray array) {
        if (array == null || array.isJsonNull() || array.size() == 0)
            return null;
        List<City> list = new ArrayList<>();
        for (JsonElement el : array) {
            list.add(new City(el.getAsJsonObject()));
        }
        return list;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    @Override
    public String toString(){
        return name;
    }
}

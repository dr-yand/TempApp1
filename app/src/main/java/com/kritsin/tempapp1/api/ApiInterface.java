package com.kritsin.tempapp1.api;

import com.google.gson.JsonElement;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.HEAD;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

public interface ApiInterface {
    @GET("/cities")
    void getCities(Callback<JsonElement> callback);
}

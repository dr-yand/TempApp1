package com.kritsin.tempapp1.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.google.gson.JsonElement;
import com.kritsin.tempapp1.BuildConfig;
import com.kritsin.tempapp1.R;
import com.kritsin.tempapp1.util.AppConfig;

import retrofit.Callback;
import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.Map;

public class ApiManager {

    private static ApiManager sInstance;

    public static ApiManager getInstance() {
        if (sInstance == null)
            sInstance = new ApiManager();
        return sInstance;
    }

    private ApiInterface mApiInterface;
    private TaskCallback mCallback;

    private ApiManager() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(AppConfig.API_ENDPOINT)
                .setLogLevel(BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
                .setErrorHandler(new ErrorHandler() {
                    @Override
                    public Throwable handleError(RetrofitError cause) {
                        if(mCallback!=null)
                            mCallback.failure(cause);
                        return cause;
                    }
                })
                .build();

        mApiInterface = adapter.create(ApiInterface.class);
    }

    public ApiManager withCallback(Context context, final Callback<JsonElement> callback) {
        mCallback = new TaskCallback(context) {
            @Override
            public void success(JsonElement element, Response response) {
                super.success(element, response);
                if(callback!=null)
                    callback.success(element, response);
            }

            @Override
            public void failure(RetrofitError error) {
                super.failure(error);
                if(callback!=null)
                    callback.failure(error);
            }
        };
        return this;
    }

    public void getCities() {
        mApiInterface.getCities(mCallback);
    }


    private class TaskCallback implements Callback<JsonElement> {

        private ProgressDialog mProgressDialog;

        public TaskCallback(Context context) {
            this(context, true);
        }

        public TaskCallback(Context context, boolean withProgress) {
            if (withProgress) {
                mProgressDialog = new ProgressDialog(context);
                mProgressDialog.setMessage(context.getString(R.string.loading));
                mProgressDialog.setCancelable(false);
                mProgressDialog.show();
            }
        }

        @Override
        public void success(JsonElement element, Response response) {
            dismissDialog();
        }

        @Override
        public void failure(RetrofitError error) {
            dismissDialog();
        }

        private void dismissDialog() {
            try {
                if (mProgressDialog != null && mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                mProgressDialog = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

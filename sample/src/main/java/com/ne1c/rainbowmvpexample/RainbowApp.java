package com.ne1c.rainbowmvpexample;

import android.app.Application;

import com.ne1c.rainbowmvp.PresenterFactory;
import com.ne1c.rainbowmvpexample.api.ReposApi;
import com.ne1c.rainbowmvpexample.presenter.RainbowPresenterStorage;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RainbowApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ReposApi reposApi = new Retrofit.Builder()
                .baseUrl("http://api.github.com")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ReposApi.class);

        PresenterFactory.init(new RainbowPresenterStorage(reposApi));
    }
}

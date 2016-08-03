package com.ne1c.rainbowmvpexample;

import android.app.Application;

import com.ne1c.rainbowmvpexample.api.ReposApi;
import com.ne1c.rainbowmvpexample.presenter.RainbowPresenterFactory;

import retrofit2.Retrofit;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ReposApi reposApi = new Retrofit.Builder()
                .baseUrl("api.github.com")
                .build()
                .create(ReposApi.class);

        RainbowPresenterFactory.init(reposApi);
    }
}

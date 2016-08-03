package com.ne1c.rainbowmvpexample.presenter;

import com.ne1c.rainbowmvp.base.BasePresenter;
import com.ne1c.rainbowmvpexample.api.ReposApi;
import com.ne1c.rainbowmvpexample.view.MainView;

public class MainPresenter extends BasePresenter<MainView>{
    public static final String TAG = MainPresenter.class.getName();

    private ReposApi mApi;

    public MainPresenter(ReposApi api) {
        mApi = api;
    }
}

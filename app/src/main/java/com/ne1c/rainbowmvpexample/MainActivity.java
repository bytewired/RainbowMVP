package com.ne1c.rainbowmvpexample;

import android.os.Bundle;

import com.ne1c.rainbowmvp.base.BaseActivity;
import com.ne1c.rainbowmvpexample.presenter.MainPresenter;

public class MainActivity extends BaseActivity<MainPresenter> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected String getPresenterTag() {
        return MainPresenter.TAG;
    }
}

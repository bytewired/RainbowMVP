package com.ne1c.rainbowmvpexample.view;

import android.support.annotation.StringRes;

import com.ne1c.rainbowmvpexample.api.RepoModel;

import java.util.ArrayList;

public interface MainView {
    void showRepos(ArrayList<RepoModel> repos);

    void showError(@StringRes int resId);

    void showProgress();
}

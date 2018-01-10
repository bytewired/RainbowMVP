package com.ne1c.rainbowmvpexample.view;

import android.support.annotation.StringRes;

import com.ne1c.rainbowmvp.base.BaseView;
import com.ne1c.rainbowmvpexample.api.RepoModel;

import java.util.ArrayList;

public interface MainView extends BaseView {
    void showRepos(ArrayList<RepoModel> repos);

    void showError(@StringRes int resId);

    void showProgress();

    void hideProgress();
}

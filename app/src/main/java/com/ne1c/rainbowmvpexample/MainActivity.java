package com.ne1c.rainbowmvpexample;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.ne1c.rainbowmvp.base.BaseActivity;
import com.ne1c.rainbowmvpexample.api.RepoModel;
import com.ne1c.rainbowmvpexample.presenter.MainPresenter;
import com.ne1c.rainbowmvpexample.view.MainView;

import java.util.ArrayList;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {
    private ListView mReposListView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mReposListView = (ListView) findViewById(R.id.repos_listView);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mPresenter.bindView(this);
        mPresenter.loadRepos();
    }

    @Override
    protected void onStop() {
        super.onStop();

        mPresenter.unbindView();
    }

    @Override
    protected String getPresenterTag() {
        return MainPresenter.TAG;
    }

    @Override
    public void showRepos(ArrayList<RepoModel> repos) {

    }

    @Override
    public void showError(@StringRes int resId) {

    }
}

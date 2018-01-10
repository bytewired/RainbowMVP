package com.ne1c.rainbowmvpexample.ui;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ne1c.rainbowmvp.annotaions.PresenterTag;
import com.ne1c.rainbowmvp.base.BaseActivity;
import com.ne1c.rainbowmvpexample.R;
import com.ne1c.rainbowmvpexample.api.RepoModel;
import com.ne1c.rainbowmvpexample.presenter.MainPresenter;
import com.ne1c.rainbowmvpexample.view.MainView;

import java.util.ArrayList;

@PresenterTag(MainPresenter.TAG)
public class MainActivity extends BaseActivity<MainPresenter> implements MainView {
    private ListView mReposListView;
    private ProgressBar mProgressBar;

    private ArrayList<RepoModel> mRepos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mReposListView = (ListView) findViewById(R.id.repos_listView);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        mRepos = new ArrayList<>();
        mReposListView.setAdapter(new ReposAdapter(mRepos));
    }

    @Override
    protected void onStart() {
        super.onStart();

        getPresenter().bindView(this);
        getPresenter().loadRepos();
    }

    @Override
    protected void onStop() {
        super.onStop();

        getPresenter().unbindView();
    }

    @Override
    public void showRepos(ArrayList<RepoModel> repos) {
        mRepos.clear();
        mRepos.addAll(repos);
        ((ReposAdapter) mReposListView.getAdapter()).notifyDataSetChanged();
    }

    @Override
    public void showError(@StringRes int resId) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }
}

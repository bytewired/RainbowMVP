package com.ne1c.rainbowmvpexample.presenter;

import com.ne1c.rainbowmvp.base.BasePresenter;
import com.ne1c.rainbowmvpexample.R;
import com.ne1c.rainbowmvpexample.api.RepoModel;
import com.ne1c.rainbowmvpexample.api.ReposApi;
import com.ne1c.rainbowmvpexample.view.MainView;

import java.util.ArrayList;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<MainView>{
    public static final String TAG = MainPresenter.class.getName();

    private ReposApi mApi;

    private ArrayList<RepoModel> mCachedRepos = new ArrayList<>();
    private boolean mIsLoading = false;

    private Subscription mSubscription;

    public MainPresenter(ReposApi api) {
        mApi = api;
    }

    public void loadRepos() {
       mSubscription = mApi.getTopAndroidRepos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<ArrayList<RepoModel>, ArrayList<RepoModel>>() {
                    @Override
                    public ArrayList<RepoModel> call(ArrayList<RepoModel> repoModels) {
                        mCachedRepos.clear();
                        mCachedRepos.addAll(repoModels);

                        return repoModels;
                    }
                })
                .filter(new Func1<ArrayList<RepoModel>, Boolean>() {
                    @Override
                    public Boolean call(ArrayList<RepoModel> repoModels) {
                        return mView != null;
                    }
                })
                .subscribe(new Action1<ArrayList<RepoModel>>() {
                    @Override
                    public void call(ArrayList<RepoModel> repoModels) {
                        mView.showRepos(repoModels);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError(R.string.something_happened);
                    }
                });
    }

    @Override
    public void onDestroy() {
        mSubscription.unsubscribe();
    }
}

package com.ne1c.rainbowmvpexample.presenter;

import com.ne1c.rainbowmvp.base.BasePresenter;
import com.ne1c.rainbowmvpexample.R;
import com.ne1c.rainbowmvpexample.api.RepoModel;
import com.ne1c.rainbowmvpexample.api.RepoResponse;
import com.ne1c.rainbowmvpexample.api.ReposApi;
import com.ne1c.rainbowmvpexample.view.MainView;

import java.util.ArrayList;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<MainView> {
    public static final String TAG = MainPresenter.class.getName();

    private ReposApi mApi;

    private ArrayList<RepoModel> mCachedRepos = new ArrayList<>();
    private boolean mIsLoading = false;

    private Subscription mSubscription;

    public MainPresenter(ReposApi api) {
        mApi = api;
    }

    @Override
    public void bindView(MainView view) {
        super.bindView(view);

        if (mIsLoading) {
            mView.showProgress();
        } else {
            mView.showRepos(mCachedRepos);
        }
    }

    public void loadRepos() {
        if (mIsLoading) {
            return;
        }

        mIsLoading = true;
        mView.showProgress();

        mSubscription = mApi.getTopAndroidRepos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<RepoResponse, ArrayList<RepoModel>>() {
                    @Override
                    public ArrayList<RepoModel> call(RepoResponse response) {
                        mCachedRepos.clear();
                        mCachedRepos.addAll(response.repos);

                        return response.repos;
                    }
                })
                .subscribe(new Action1<ArrayList<RepoModel>>() {
                    @Override
                    public void call(ArrayList<RepoModel> repoModels) {
                        mIsLoading = false;
                        if (mView != null) {
                            mView.showRepos(repoModels);
                            mView.hideProgress();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mIsLoading = false;
                        if (mView != null) {
                            mView.showError(R.string.something_happened);
                            mView.hideProgress();
                        }
                    }
                });
    }

    @Override
    public void onDestroy() {
        mSubscription.unsubscribe();
    }
}

package com.ne1c.rainbowmvpexample.presenter;

import com.ne1c.rainbowmvp.ViewState;
import com.ne1c.rainbowmvp.ViewStateListener;
import com.ne1c.rainbowmvp.base.BasePresenter;
import com.ne1c.rainbowmvpexample.R;
import com.ne1c.rainbowmvpexample.api.RepoModel;
import com.ne1c.rainbowmvpexample.api.RepoResponse;
import com.ne1c.rainbowmvpexample.api.ReposApi;
import com.ne1c.rainbowmvpexample.view.MainView;

import java.util.ArrayList;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<MainView> implements ViewStateListener {
    public static final String TAG = MainPresenter.class.getName();

    private ReposApi mApi;

    private ArrayList<RepoModel> mCachedRepos = new ArrayList<>();

    private Subscription mSubscription;

    public MainPresenter(ReposApi api) {
        mApi = api;
        addViewStateListener(this);
    }

    public void loadRepos() {
        if (getViewState() == ViewState.IN_PROGRESS) {
            return;
        }

        if (mCachedRepos.size() > 0) {
            getView().showRepos(mCachedRepos);
            return;
        }

        mSubscription = mApi.getTopAndroidRepos()
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        setViewState(ViewState.IN_PROGRESS);
                    }
                })
                .map(new Func1<RepoResponse, ArrayList<RepoModel>>() {
                    @Override
                    public ArrayList<RepoModel> call(RepoResponse response) {
                        mCachedRepos.clear();
                        mCachedRepos.addAll(response.repos);

                        return response.repos;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ArrayList<RepoModel>>() {
                    @Override
                    public void call(ArrayList<RepoModel> repoModels) {
                        setViewState(ViewState.FINISH, false);

                        if (getView() != null) {
                            getView().showRepos(repoModels);
                            getView().hideProgress();
                            setViewState(ViewState.NOTHING);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        setViewState(ViewState.ERROR, false);

                        if (getView() != null) {
                            getView().showError(R.string.something_happened);
                            getView().hideProgress();

                            setViewState(ViewState.NOTHING);
                        }
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mSubscription.unsubscribe();
    }

    @Override
    public void stateChanged(ViewState state) {
        if (state == ViewState.IN_PROGRESS) {
            getView().showProgress();
        }

        if (state == ViewState.FINISH) {
            getView().showRepos(mCachedRepos);
            getView().hideProgress();
        }

        if (state == ViewState.ERROR) {
            getView().hideProgress();
            getView().showError(R.string.something_happened);
        }
    }
}

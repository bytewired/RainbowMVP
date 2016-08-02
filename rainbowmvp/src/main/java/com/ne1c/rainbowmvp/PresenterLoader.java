package com.ne1c.rainbowmvp;

import android.content.Context;
import android.support.v4.content.Loader;

import com.ne1c.rainbowmvp.base.BasePresenter;

public class PresenterLoader<P extends BasePresenter> extends Loader<P> {
    private P mPresenter;

    private String mTagPresenter;

    public PresenterLoader(Context context, String tagPresenter) {
        super(context);

        mTagPresenter = tagPresenter;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        if (mPresenter != null) {
            deliverResult(mPresenter);
            return;
        }

        forceLoad();
    }

    @Override
    protected void onForceLoad() {
        mPresenter = (P) PresenterFactory.create(mTagPresenter);
        deliverResult(mPresenter);
    }

    @Override
    protected void onReset() {
        mPresenter = null;
    }
}

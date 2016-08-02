package com.ne1c.rainbowmvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.ne1c.rainbowmvp.PresenterLoader;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements LoaderManager.LoaderCallbacks<P> {
    protected final int LOADER_ID = 593;

    protected P mPresenter;

    private boolean mPresenterDelivered;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public Loader<P> onCreateLoader(int id, Bundle args) {
        return new PresenterLoader<>(getActivity(), getPresenterTag());
    }

    @Override
    public void onLoadFinished(Loader<P> loader, P presenter) {
        if (!mPresenterDelivered) {
            mPresenter = presenter;
            mPresenterDelivered = true;
        }
    }

    @Override
    public void onLoaderReset(Loader<P> loader) {
        mPresenter = null;
    }

    protected abstract String getPresenterTag();
}

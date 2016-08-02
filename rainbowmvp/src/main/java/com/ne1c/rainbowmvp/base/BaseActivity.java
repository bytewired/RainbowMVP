package com.ne1c.rainbowmvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import com.ne1c.rainbowmvp.PresenterLoader;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements LoaderManager.LoaderCallbacks<P> {
    protected final int LOADER_ID = 342;

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public Loader<P> onCreateLoader(int id, Bundle args) {
        return new PresenterLoader<>(this, getPresenterTag());
    }

    @Override
    public void onLoadFinished(Loader<P> loader, P presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onLoaderReset(Loader<P> loader) {
        mPresenter = null;
    }

    protected abstract String getPresenterTag();
}

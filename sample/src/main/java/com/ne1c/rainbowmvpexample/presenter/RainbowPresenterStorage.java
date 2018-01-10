package com.ne1c.rainbowmvpexample.presenter;

import com.ne1c.rainbowmvp.PresenterStorage;
import com.ne1c.rainbowmvp.base.BasePresenter;
import com.ne1c.rainbowmvpexample.api.ReposApi;

public class RainbowPresenterStorage implements PresenterStorage {
    private ReposApi mApi;

    public RainbowPresenterStorage(ReposApi api) {
        mApi = api;
    }

    @Override
    public BasePresenter create(String tag) {
        if (tag.equals(MainPresenter.TAG)) {
            return new MainPresenter(mApi);
        }

        return null;
    }
}

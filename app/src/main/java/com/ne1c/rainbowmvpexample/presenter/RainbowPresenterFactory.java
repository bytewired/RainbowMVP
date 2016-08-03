package com.ne1c.rainbowmvpexample.presenter;

import com.ne1c.rainbowmvp.PresenterFactory;
import com.ne1c.rainbowmvp.base.BasePresenter;
import com.ne1c.rainbowmvpexample.api.ReposApi;

public class RainbowPresenterFactory extends PresenterFactory {
    private ReposApi mApi;

    private RainbowPresenterFactory(ReposApi api) {
        mApi = api;
    }

    public static void init(ReposApi api) {
        mInstance = new RainbowPresenterFactory(api);
    }

    @Override
    public BasePresenter create(String tag) {
        if (tag.equals(MainPresenter.TAG)) {
            return new MainPresenter(mApi);
        }
        return super.create(tag);
    }
}

package com.ne1c.rainbowmvp;

import com.ne1c.rainbowmvp.base.BasePresenter;

public class PresenterFactory {
    /**
     * Need to init
     */
    protected static PresenterFactory mInstance;

    public static PresenterFactory getInstance() {
        return mInstance;
    }

    /**
     * Create new instance of presenter
     *
     * @param tag keyword of presenter
     * @return new instance of requeried presenter
     */
    public BasePresenter create(String tag) {
        return null;
    }
}

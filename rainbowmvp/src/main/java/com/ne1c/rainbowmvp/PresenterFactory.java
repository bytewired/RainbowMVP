package com.ne1c.rainbowmvp;

import com.ne1c.rainbowmvp.base.BasePresenter;

public class PresenterFactory {
    protected static PresenterFactory INSTANCE;

    /**
     * In this method your can put all your dependencies
     */
    public static void init() {
        INSTANCE = new PresenterFactory();
    }

    /**
     * Create new instance of presenter
     *
     * @param tag keyword of presenter
     * @return new instance of requeried presenter
     */
    public static BasePresenter create(String tag) {
        return null;
    }
}

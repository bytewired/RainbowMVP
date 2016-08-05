package com.ne1c.rainbowmvp;

import com.ne1c.rainbowmvp.base.BasePresenter;

public class PresenterFactory {
    private static PresenterFactory mInstance;

    private PresenterStorage mPresenterStorage;

    private PresenterFactory(PresenterStorage storage) {
        mPresenterStorage = storage;
    }

    public static void init(PresenterStorage storage) {
        mInstance = new PresenterFactory(storage);
    }

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
        return mPresenterStorage.create(tag);
    }
}

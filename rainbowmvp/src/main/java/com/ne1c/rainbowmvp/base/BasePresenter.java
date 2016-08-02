package com.ne1c.rainbowmvp.base;

public abstract class BasePresenter<V> {
    protected V mView;

    public void bindView(V view) {
        mView = view;
    }

    public void unbindView() {
        mView = null;
    }
}

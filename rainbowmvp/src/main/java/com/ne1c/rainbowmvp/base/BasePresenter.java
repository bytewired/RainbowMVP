package com.ne1c.rainbowmvp.base;

import com.ne1c.rainbowmvp.ViewState;
import com.ne1c.rainbowmvp.ViewStateListener;

public abstract class BasePresenter<V> {
    protected V mView;

    private ViewState mLastState = ViewState.EMPTY;
    private ViewStateListener mViewStateListener;

    public void bindView(V view) {
        mView = view;

        if (mViewStateListener != null) {
            mViewStateListener.stateChanged(mLastState);
        }
    }

    public void unbindView() {
        mView = null;
    }

    public void onDestroy() {
        removeViewStateListener();
    }

    public void setViewState(ViewState state) {
        mLastState = state;

        if (mViewStateListener != null) {
            mViewStateListener.stateChanged(state);
        }
    }

    public void setViewState(ViewState state, boolean callListener) {
        mLastState = state;

        if (callListener && mViewStateListener != null) {
            mViewStateListener.stateChanged(state);
        }
    }

    public ViewState getViewState() {
        return mLastState;
    }

    public void addViewStateListener(ViewStateListener listener) {
        mViewStateListener = listener;
    }

    public void removeViewStateListener() {
        mViewStateListener = null;
    }
}

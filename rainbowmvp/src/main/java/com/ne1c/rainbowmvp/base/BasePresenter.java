/*
 * Copyright 2016 Nikolay Kucheriaviy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ne1c.rainbowmvp.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ne1c.rainbowmvp.ViewState;
import com.ne1c.rainbowmvp.ViewStateListener;

public abstract class BasePresenter<V extends BaseView> {
    private V mView;

    private ViewState mLastState = ViewState.NOTHING;
    private ViewStateListener mViewStateListener;

    public void bindView(@NonNull V view) {
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

    @Nullable
    public V getView() {
        return mView;
    }

    public void setViewState(ViewState state) {
        mLastState = state;

        if (mViewStateListener != null && mView != null) {
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

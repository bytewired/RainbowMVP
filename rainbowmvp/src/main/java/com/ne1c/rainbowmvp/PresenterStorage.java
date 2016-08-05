package com.ne1c.rainbowmvp;

import com.ne1c.rainbowmvp.base.BasePresenter;

public interface PresenterStorage {
    BasePresenter create(String tag);
}

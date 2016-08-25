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

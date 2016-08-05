# RainbowMVP
Lightweight MVP library(for personal using) with easy lifecycle.

For good understanding approach read this [article](https://medium.com/@czyrux/presenter-surviving-orientation-changes-with-loaders-6da6d86ffbbf).

* Really lighweight library
* Easy integrate to project
* Minimum actions for implement MVP
* Doesn't destroy presenter after rotation device
* You can cached date in presenter for restore date after rotate device

In presenter you have 3 methods:
- bindView(V view) - you need call this method for attach your view for presenter.
- unbindView() - you need call this method when view not available.
- onDestoy() - call, when you presenter will destroy.

# Example how to integrate library in yout project:

# Step 1
Create you View interface:

```java
public interface MyView {
    void showResult(String text);
    
    void showError(@StringRes int resId);
}
```

# Step 2
Create presenter. You need inherit of BasePresenter and add tag:

```java
public class MyPresenter extends BasePresenter<MyView> {
    public static final TAG = MyPresenter.class.getName();
    ...
    public void makeParty() {
        // some actions
        boolean success = ...
        if (success) {
            mView.showResult(...);
        } else {
            mView.showError(R.string.error);
        }
    }
    ...
}
```
# Step 3
* Implement PresenterStorage:
```java
public class MyPresenterStorage implements PresenterStorage {
    public MyPresenterStorage(...) {
        ...
    }

    @Override
    public BasePresenter create(String tag) {
        if (tag.equals(MyPresenter.TAG)) {
            return new MyPresenter(...);
        }

        return null;
    }
}
```

# Step 4
* Init PresenterFactory, before use any presenter. You can do it in onCreate() of Applicaton or splash screen:
```java
public class MyApplication extends android.app.Application {
    @Override
    public void onCreate() {
        ...
        PresenterFactory.init(new MyPresenterStorage(...));
    }
}
```

# Step 4
Your activity or fragment need to inherit of BaseActivity/BaseFragment and override getPresenterTag():
```java
public class MyActivity extends BaseActivity<MyPresenter> implement MyView {
    ...
    @Override
    public String getPresenterTag() {
        return MyPresenter.TAG;
    }
    ...
}
```

<h2>
    <a id="user-content-license" class="anchor" href="#license" aria-hidden="true">
    <span class="octicon octicon-link"></span></a>License
</h2>
<pre><code>Copyright 2016 Mykola Kucheriaviy

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
</code></pre>

package com.pky.canteen.base.mvp;

import android.os.Bundle;
import android.view.View;

import com.pky.canteen.base.call.Base;
import com.pky.canteen.base.call.ViewGet;

public class BaseView<P extends Base> implements MvpLifecycle, ViewGet {
    protected P base;

    @Override
    public void onCreate(Base base, Bundle savedInstanceState) {
        this.base = (P) base;
    }

    @Override
    public void onStart(Base base, Bundle savedInstanceState) {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        this.base = null;
    }

    @Override
    public void onViewCreated(Base base, Bundle savedInstanceState) {

    }

    @Override
    public <T extends View> T get(int viewId) {
        if (base != null) {
            return base.get(viewId);
        }
        return null;
    }
}

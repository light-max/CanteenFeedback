package com.pky.canteen.ui.collect;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.pky.canteen.R;
import com.pky.canteen.base.activity.BaseActivity;

public class CollectActivity extends BaseActivity<CollectModel, CollectView> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_collect;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideActionBar();
        getView().getAdapter().setOnCancelCollectListener((data, position) -> {
            getModel().unCollect(data.getId());
        });
    }
}

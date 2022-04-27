package com.pky.canteen.ui.opinion;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.pky.canteen.R;
import com.pky.canteen.base.activity.BaseActivity;

public class OpinionActivity extends BaseActivity<OpinionModel, OpinionView> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_opinion;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideActionBar();
    }
}

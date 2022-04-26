package com.pky.canteen.ui.stall;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pky.canteen.R;
import com.pky.canteen.base.activity.BaseActivity;

public class StallActivity extends BaseActivity<StallModel, StallView> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_stall_details;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            map("id", savedInstanceState.getInt("id"));
        } else {
            map("id", getIntent().getIntExtra("stallId", 0));
        }
        super.onCreate(savedInstanceState);
        hideActionBar();
    }

    public static void start(Context context, int stallId) {
        Intent intent = new Intent(context, StallActivity.class);
        intent.putExtra("stallId", stallId);
        context.startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("id", map("id"));
    }
}

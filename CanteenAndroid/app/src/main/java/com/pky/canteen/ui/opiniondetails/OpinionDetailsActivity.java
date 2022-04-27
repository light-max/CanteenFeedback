package com.pky.canteen.ui.opiniondetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.pky.canteen.R;
import com.pky.canteen.base.activity.BaseActivity;

public class OpinionDetailsActivity extends BaseActivity<OpinionDetailsModel, OpinionDetailsView> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_opinion_details;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        map("id", getIntent().getIntExtra("id", 0));
        super.onCreate(savedInstanceState);
        hideActionBar();
    }

    public static void start(Context context, int id) {
        Intent intent = new Intent(context, OpinionDetailsActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }
}

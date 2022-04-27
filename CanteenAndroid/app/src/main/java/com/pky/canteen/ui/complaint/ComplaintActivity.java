package com.pky.canteen.ui.complaint;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.pky.canteen.R;
import com.pky.canteen.base.activity.BaseActivity;

public class ComplaintActivity extends BaseActivity<ComplaintModel, ComplaintView> {
    public int feedbackId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_complaint;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        feedbackId = getIntent().getIntExtra("feedbackId", feedbackId);
        super.onCreate(savedInstanceState);
        hideActionBar();
    }

    public static void start(Context context, int feedbackId) {
        Intent intent = new Intent(context, ComplaintActivity.class);
        intent.putExtra("feedbackId", feedbackId);
        context.startActivity(intent);
    }
}

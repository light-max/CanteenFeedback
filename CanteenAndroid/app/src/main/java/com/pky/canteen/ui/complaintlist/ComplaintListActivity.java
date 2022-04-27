package com.pky.canteen.ui.complaintlist;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.pky.canteen.R;
import com.pky.canteen.base.activity.BaseActivity;

public class ComplaintListActivity extends BaseActivity<ComplaintListModel, ComplaintListView> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_complaint_list;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideActionBar();
    }
}

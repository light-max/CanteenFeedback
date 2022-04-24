package com.pky.canteen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Nullable;

import com.pky.canteen.base.activity.NoMvpActivity;
import com.pky.canteen.ui.login.LoginActivity;


public class MainActivity extends NoMvpActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideActionBar();
        hideStatusBar();
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra(LoginActivity.auto, true);
            startActivity(intent);
            finish();
        }, 1000);
    }
}
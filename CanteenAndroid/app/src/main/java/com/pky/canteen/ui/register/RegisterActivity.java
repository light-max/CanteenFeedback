package com.pky.canteen.ui.register;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.pky.canteen.R;
import com.pky.canteen.base.activity.BaseActivity;

public class RegisterActivity extends BaseActivity<RegisterModel, RegisterView> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideActionBar();
        view.getPost().setOnClickListener(v -> {
            if (view.getPassword().equals(view.getPassword1())) {
                model.login(view.isStudent(),
                        view.getUsername(),
                        view.getName(),
                        view.getPassword()
                );
            } else {
                toast("两次输入的密码不一致");
            }
        });
    }
}

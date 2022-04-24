package com.pky.canteen.ui.login;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.pky.canteen.R;
import com.pky.canteen.base.call.Base;
import com.pky.canteen.base.mvp.BaseView;

public class LoginView extends BaseView<LoginActivity> {
    private EditText username;
    private EditText password;
    private CheckBox remember;
    private CheckBox auto;
    private TextView post;
    private Spinner role;
    private AlertDialog dialog;
    private TextView register;

    @Override
    public void onCreate(Base base, Bundle savedInstanceState) {
        super.onCreate(base, savedInstanceState);
        username = get(R.id.username);
        password = get(R.id.password);
        remember = get(R.id.remember);
        auto = get(R.id.auto);
        post = get(R.id.post);
        role = get(R.id.role);
        register = get(R.id.register);

        SharedPreferences sp = this.base.getPreferences(0);
        if (sp.getBoolean("remember", false)) {
            remember.setChecked(true);
            username.setText(sp.getString("username", ""));
            password.setText(sp.getString("password", ""));
            role.setSelection(sp.getInt("role", 0));
            auto.setChecked(sp.getBoolean("auto", false));
        }

        auto.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                remember.setChecked(true);
            }
        });
        remember.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (!isChecked) {
                auto.setChecked(false);
            }
        });
    }

    public void showDialog() {
        ProgressBar progressBar = new ProgressBar(base.getContext());
        dialog = new AlertDialog.Builder(base.getContext())
                .setCancelable(false)
                .setMessage("登录中...")
                .setView(progressBar)
                .show();
    }

    public void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    public String getUsername() {
        return username.getText().toString();
    }

    public String getPassword() {
        return password.getText().toString();
    }

    public TextView getPost() {
        return post;
    }

    public TextView getRegister() {
        return register;
    }

    public boolean isStudent() {
        return role.getSelectedItemPosition() == 0;
    }

    public boolean isAuto() {
        return auto.isChecked();
    }

    public boolean isRemember() {
        return remember.isChecked();
    }

    public void saveStatus() {
        base.getPreferences(0).edit()
                .putBoolean("auto", isAuto())
                .putBoolean("remember", isRemember())
                .putString("username", getUsername())
                .putString("password", getPassword())
                .putInt("role", isStudent() ? 0 : 1)
                .apply();
    }
}

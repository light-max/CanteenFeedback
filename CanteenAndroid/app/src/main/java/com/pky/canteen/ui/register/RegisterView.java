package com.pky.canteen.ui.register;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.pky.canteen.R;
import com.pky.canteen.base.call.Base;
import com.pky.canteen.base.mvp.BaseView;

public class RegisterView extends BaseView<RegisterActivity> {
    private EditText username;
    private EditText name;
    private EditText password;
    private EditText password1;
    private Spinner role;
    private TextView post;
    private AlertDialog dialog;

    @Override
    public void onCreate(Base base, Bundle savedInstanceState) {
        super.onCreate(base, savedInstanceState);
        click(R.id.back, () -> base.getActivity().finish());
        username = get(R.id.username);
        name = get(R.id.name);
        password = get(R.id.password);
        password1 = get(R.id.password1);
        role = get(R.id.role);
        post = get(R.id.post);
    }

    public String getUsername() {
        return username.getText().toString();
    }

    public String getName() {
        return name.getText().toString();
    }

    public String getPassword() {
        return password.getText().toString();
    }

    public String getPassword1() {
        return password1.getText().toString();
    }

    public boolean isStudent() {
        return role.getSelectedItemPosition() == 0;
    }

    public TextView getPost() {
        return post;
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
}

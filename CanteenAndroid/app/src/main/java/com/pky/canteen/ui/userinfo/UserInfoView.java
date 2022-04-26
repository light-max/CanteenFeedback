package com.pky.canteen.ui.userinfo;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.pky.canteen.R;
import com.pky.canteen.api.Api;
import com.pky.canteen.base.call.Base;
import com.pky.canteen.base.mvp.BaseView;
import com.pky.canteen.data.livedata.StudentData;
import com.pky.canteen.data.livedata.TeacherData;
import com.pky.canteen.utils.PermissionUtil;

public class UserInfoView extends BaseView<UserInfoActivity> {
    private ImageView head;
    private TextView name;
    private TextView des;
    private TextView phone;
    private TextView gender;

    private AlertDialog dialog;

    @Override
    public void onCreate(Base base, Bundle savedInstanceState) {
        super.onCreate(base, savedInstanceState);
        click(R.id.back, () -> base.getActivity().finish());
        head = get(R.id.head);
        name = get(R.id.name);
        des = get(R.id.des);
        phone = get(R.id.phone);
        gender = get(R.id.gender);
        setViewData();
        click(R.id.view_head, () -> {
            if (PermissionUtil.localStorage(base.getActivity())) {
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                base.getActivity().startActivityForResult(intent, UserInfoActivity.PICTURE);
            } else {
                base.toast("没有文件读取权限");
            }
        });
        UserInfoModel model = this.base.getModel();
        click(R.id.view_name, () -> openEditDialog(
                "姓名",
                name.getText().toString(),
                model::setName
        ));
        click(R.id.view_des, () -> openEditDialog(
                "简介",
                des.getText().toString(),
                model::setDes
        ));
        click(R.id.view_phone, () -> openEditDialog(
                "电话",
                phone.getText().toString(),
                model::setPhone
        ));
        click(R.id.view_gender, () -> {
            base.map("selectGender", null);
            new AlertDialog.Builder(base.getContext()).setSingleChoiceItems(
                    new String[]{"隐藏", "男", "女"},
                    model.getGender(),
                    (dialog, which) -> base.map("selectGender", which))
                    .setNegativeButton("取消", null)
                    .setPositiveButton("确定", (dialog, which) -> {
                        if (base.map("selectGender") != null) {
                            model.setGender(base.map("selectGender"));
                        }
                    }).show();
        });
    }

    public void setViewData() {
        StudentData.getInstance().observe(base, student -> {
            Glide.with(head)
                    .load(Api.getHeadUrl(student.getUid()))
                    .apply(new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
                    .into(head);
            name.setText(student.getName());
            des.setText(student.getDes());
            phone.setText(student.getPhone());
            gender.setText(new String[]{"隐藏", "男", "女"}[student.getGender()]);
            base.getModel().setStudent(true);
        });
        TeacherData.getInstance().observe(base, teacher -> {
            Glide.with(head)
                    .load(Api.getHeadUrl(teacher.getUid()))
                    .apply(new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
                    .into(head);
            name.setText(teacher.getName());
            des.setText(teacher.getDes());
            phone.setText(teacher.getPhone());
            gender.setText(new String[]{"隐藏", "男", "女"}[teacher.getGender()]);
            base.getModel().setStudent(false);
        });
    }

    public void showDialog() {
        dialog = new AlertDialog.Builder(base.getContext())
                .setView(new ProgressBar(base.getContext()))
                .setCancelable(false)
                .show();
    }

    public void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
        dialog = null;
    }

    public void openEditDialog(String title, String source, OnEditListener listener) {
        EditText text = new EditText(base.getContext());
        text.setText(source);
        new AlertDialog.Builder(base.getContext())
                .setView(text)
                .setMessage("请输入" + title)
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", (dialog, which) -> {
                    listener.onEditText(text.getText().toString());
                }).show();
    }

    public ImageView getHead() {
        return head;
    }

    public TextView getName() {
        return name;
    }

    public TextView getDes() {
        return des;
    }

    public TextView getPhone() {
        return phone;
    }

    public TextView getGender() {
        return gender;
    }

    interface OnEditListener {
        void onEditText(String text);
    }
}

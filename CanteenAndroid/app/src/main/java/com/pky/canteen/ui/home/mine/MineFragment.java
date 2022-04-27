package com.pky.canteen.ui.home.mine;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.pky.canteen.R;
import com.pky.canteen.api.Api;
import com.pky.canteen.base.fragment.NoMvpFragment;
import com.pky.canteen.data.livedata.StudentData;
import com.pky.canteen.data.livedata.TeacherData;
import com.pky.canteen.ui.collect.CollectActivity;
import com.pky.canteen.ui.complaintlist.ComplaintListActivity;
import com.pky.canteen.ui.login.LoginActivity;
import com.pky.canteen.ui.opinion.OpinionActivity;
import com.pky.canteen.ui.userinfo.UserInfoActivity;

public class MineFragment extends NoMvpFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView head = get(R.id.head);
        TextView name = get(R.id.name);
        TextView type = get(R.id.type);
        TextView uid = get(R.id.uid);
        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE);
        StudentData.getInstance().observe(this, student -> {
            Glide.with(this)
                    .load(Api.getHeadUrl(student.getUid()))
                    .apply(options)
                    .into(head);
            name.setText(student.getName());
            type.setText("学生");
            uid.setText("UID：" + student.getUid());
        });
        TeacherData.getInstance().observe(this, teacher -> {
            Glide.with(this)
                    .load(Api.getHeadUrl(teacher.getUid()))
                    .apply(options)
                    .into(head);
            name.setText(teacher.getName());
            type.setText("教师");
            uid.setText("UID：" + teacher.getUid());
        });
        click(R.id.view_info, () -> {
            Intent intent = new Intent(getContext(), UserInfoActivity.class);
            startActivity(intent);
        });
        click(R.id.view_collect, () -> {
            Intent intent = new Intent(getContext(), CollectActivity.class);
            startActivity(intent);
        });
        click(R.id.view_opinion, () -> {
            Intent intent = new Intent(getContext(), OpinionActivity.class);
            startActivity(intent);
        });
        click(R.id.view_complaint, () -> {
            Intent intent = new Intent(getContext(), ComplaintListActivity.class);
            startActivity(intent);
        });
        click(R.id.view_logout, () -> {
            new AlertDialog.Builder(getContext())
                    .setMessage("你确定要退出登录吗?")
                    .setNegativeButton("取消", null)
                    .setPositiveButton("确定", (dialog, which) -> {
                        Api.logout();
                        requireActivity().finish();
                        Intent intent = new Intent(getContext(), LoginActivity.class);
                        intent.putExtra(LoginActivity.auto, false);
                        startActivity(intent);
                    })
                    .show();
        });
    }
}

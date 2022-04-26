package com.pky.canteen.ui.opinionsend;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.pky.canteen.R;
import com.pky.canteen.base.activity.BaseActivity;
import com.pky.canteen.utils.FileUtil;
import com.pky.canteen.utils.PermissionUtil;

import java.io.File;

public class OpinionSendActivity extends BaseActivity<OpinionSendModel, OpinionSendView> {
    private final int VIDEO = 0x2;
    private final int PICTURE = 0x3;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_opinion_send;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideActionBar();
        map("dishId", getIntent().getIntExtra("dishId", 0));
    }

    public static void start(Context context, int dishId) {
        Intent intent = new Intent(context, OpinionSendActivity.class);
        intent.putExtra("dishId", dishId);
        context.startActivity(intent);
    }

    public void video() {
        if (PermissionUtil.localStorage(this)) {
            Intent intent = new Intent(Intent.ACTION_PICK, null);
            intent.setDataAndType(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, "video/*");
            startActivityForResult(intent, VIDEO);
        } else {
            toast("没有文件读取权限");
        }
    }

    public void picture() {
        if (PermissionUtil.localStorage(this)) {
            Intent intent = new Intent(Intent.ACTION_PICK, null);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(intent, PICTURE);
        } else {
            toast("没有文件读取权限");
        }
    }

    @SuppressLint("Recycle")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
        }
        // 选择照片
        else if (requestCode == PICTURE) {
            Uri uri = data.getData();
            assert uri != null;
            String filePath = FileUtil.getFilePathByUri(this, uri);
            getView().getAdapter().getData().remove(filePath);
            getView().getAdapter().getData().add(filePath);
            getView().getAdapter().notifyDataSetChanged();
        }
        // 选择视频
        else if (requestCode == VIDEO) {
            Uri uri = data.getData();
            assert uri != null;
            String filePath = FileUtil.getFilePathByUri(this, uri);
            getModel().setVideoPath(filePath);
            get(R.id.video_view).setVisibility(View.VISIBLE);
            TextView videoName = get(R.id.video_name);
            videoName.setText(new File(filePath).getName());
        }
    }
}

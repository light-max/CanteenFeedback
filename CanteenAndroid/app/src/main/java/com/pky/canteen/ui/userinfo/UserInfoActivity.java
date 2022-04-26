package com.pky.canteen.ui.userinfo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.pky.canteen.R;
import com.pky.canteen.base.activity.BaseActivity;
import com.pky.canteen.utils.FileUtil;

import java.io.File;

public class UserInfoActivity extends BaseActivity<UserInfoModel, UserInfoView> {
    public static final int PICTURE = 0x2;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideActionBar();
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
            File file = new File(filePath);
            getModel().setHeadImage(file);
        }
    }
}

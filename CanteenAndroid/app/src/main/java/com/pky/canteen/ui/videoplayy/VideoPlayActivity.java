package com.pky.canteen.ui.videoplayy;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.Nullable;

import com.pky.canteen.R;
import com.pky.canteen.base.activity.NoMvpActivity;

public class VideoPlayActivity extends NoMvpActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_play;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideActionBar();
        VideoView video = get(R.id.video);
        Uri uri = Uri.parse(getIntent().getStringExtra("url"));
        video.setVideoURI(uri);
        video.setMediaController(new MediaController(this));
        video.start();
    }

    public static void start(Context context, String url) {
        Intent intent = new Intent(context, VideoPlayActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }
}

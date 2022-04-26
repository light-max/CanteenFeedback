package com.pky.canteen.ui.opinionsend;

import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;

import com.pky.canteen.api.Api;
import com.pky.canteen.async.Async;
import com.pky.canteen.base.mvp.BaseModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OpinionSendModel extends BaseModel<OpinionSendActivity> {
    private String videoPath = null;

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public void post() {
        OpinionSendView view = base.getView();
        String content = view.getContent().getText().toString();
        List<String> images = view.getAdapter().getData();
        List<File> files = new ArrayList<>();
        for (String image : images) {
            files.add(new File(image));
        }
        Integer dishId = base.map("dishId");
        File video = videoPath == null ? null : new File(videoPath);
        Api.postOpinion(dishId, content, files, video)
                .before(view::showDialog)
                .after(view::dismissDialog)
                .error((message, e) -> base.toast(message))
                .success(() -> {
                    base.toast("上传成功");
                    base.finish();
                }).run();

        Async.builder().task(() -> {
            if (videoPath == null) return;
            try {
                MediaCodec vCodec = MediaCodec.createEncoderByType(MediaFormat.MIMETYPE_VIDEO_AVC);
                MediaExtractor extractor = new MediaExtractor();
                extractor.setDataSource(videoPath);
                for (int i = 0; i < extractor.getTrackCount(); i++) {
//                    extractor
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).run();
    }
}

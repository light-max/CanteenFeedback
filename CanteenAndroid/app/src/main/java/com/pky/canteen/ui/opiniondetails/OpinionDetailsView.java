package com.pky.canteen.ui.opiniondetails;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pky.canteen.R;
import com.pky.canteen.api.ExRequestBuilder;
import com.pky.canteen.base.call.Base;
import com.pky.canteen.base.mvp.BaseView;
import com.pky.canteen.data.result.Opinion;
import com.pky.canteen.ui.complaint.ComplaintActivity;
import com.pky.canteen.ui.videoplayy.VideoPlayActivity;

public class OpinionDetailsView extends BaseView<OpinionDetailsActivity> {
    @Override
    public void onCreate(Base base, Bundle savedInstanceState) {
        super.onCreate(base, savedInstanceState);
        click(R.id.back, () -> base.getActivity().finish());
    }

    public void setData(Opinion data) {
        TextView content = get(R.id.content);
        content.setText(data.getContent());

        if (!data.getImages().isEmpty()) {
            get(R.id.view_images).setVisibility(View.VISIBLE);
            RecyclerView recycler = get(R.id.images);
            recycler.setLayoutManager(new GridLayoutManager(base.getContext(), 3));
            int width = base.getActivity()
                    .getWindowManager()
                    .getDefaultDisplay()
                    .getWidth();
            ImageAdapter adapter = new ImageAdapter(width / 3);
            recycler.setAdapter(adapter);
            adapter.setNewData(data.getImages());
        }

        if (data.getVideo() != null) {
            get(R.id.view_video).setVisibility(View.VISIBLE);
            click(R.id.play, () -> {
                String url = ExRequestBuilder.getUrl(data.getVideo());
                url += "?range=true";
                VideoPlayActivity.start(base.getContext(), url);
            });
        }

        if (data.getFeedbackId() != null) {
            get(R.id.view_feedback).setVisibility(View.VISIBLE);
            TextView feedback = get(R.id.feedback);
            this.base.getModel().requestFeedback(data.getFeedbackId())
                    .success(data1 -> {
                        feedback.setText(data1.getContent());
                    }).run();
            click(R.id.complaint, () -> {
                ComplaintActivity.start(base.getContext(), data.getFeedbackId());
            });
        } else {
            get(R.id.view_no_feedback).setVisibility(View.VISIBLE);
        }
    }
}

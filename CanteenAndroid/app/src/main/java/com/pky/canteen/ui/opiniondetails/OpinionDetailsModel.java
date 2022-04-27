package com.pky.canteen.ui.opiniondetails;

import android.os.Bundle;

import com.pky.canteen.api.Api;
import com.pky.canteen.async.Async;
import com.pky.canteen.base.call.Base;
import com.pky.canteen.base.mvp.BaseModel;
import com.pky.canteen.data.result.Feedback;

public class OpinionDetailsModel extends BaseModel<OpinionDetailsActivity> {
    @Override
    public void onCreate(Base base, Bundle savedInstanceState) {
        super.onCreate(base, savedInstanceState);
        requestData();
    }

    public void requestData() {
        Api.getOpinion(base.map("id"))
                .error((message, e) -> base.toast(message))
                .success(data -> {
                    base.getView().setData(data);
                }).run();
    }

    public Async.Builder<Feedback> requestFeedback(int feedbackId) {
        return Api.getFeedbackById(feedbackId)
                .error((message, e) -> base.toast(message));
    }
}

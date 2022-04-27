package com.pky.canteen.ui.complaint;

import android.os.Bundle;

import com.pky.canteen.api.Api;
import com.pky.canteen.base.call.Base;
import com.pky.canteen.base.mvp.BaseModel;

public class ComplaintModel extends BaseModel<ComplaintActivity> {
    @Override
    public void onCreate(Base base, Bundle savedInstanceState) {
        super.onCreate(base, savedInstanceState);
        requestData();
    }

    public void requestData() {
        Api.getComplaintDetails(base.feedbackId)
                .error((message, e) -> base.toast(message))
                .success(data -> {
                    base.getView().setData(data);
                }).run();
    }

    public void sendComplaint(int feedbackId, String content) {
        Api.sendComplaint(feedbackId, content)
                .error((message, e) -> base.toast(message))
                .success(this::requestData)
                .run();
    }
}

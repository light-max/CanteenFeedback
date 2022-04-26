package com.pky.canteen.ui.home.message;

import android.os.Bundle;
import android.view.View;

import com.pky.canteen.api.Api;
import com.pky.canteen.base.call.Base;
import com.pky.canteen.base.mvp.BaseModel;

public class MessageModel extends BaseModel<MessageFragment> {
    @Override
    public void onViewCreated(Base base, Bundle savedInstanceState) {
        super.onViewCreated(base, savedInstanceState);
        requestData();
    }

    public void requestData() {
        Api.getFeedbackMsgAll()
                .error((message, e) -> base.toast(message))
                .success(data -> {
                    MessageView view = base.getV();
                    if (data.isEmpty()) {
                        view.getEmpty().setVisibility(View.VISIBLE);
                        view.getRecycler().setVisibility(View.GONE);
                    } else {
                        view.getEmpty().setVisibility(View.GONE);
                        view.getRecycler().setVisibility(View.VISIBLE);
                        view.getAdapter().setNewData(data);
                    }
                }).run();
    }

    public void read(int id) {
        Api.readFeedbackMsg(id)
                .error((message, e) -> base.toast(message))
//                .success(this::requestData)
                .run();
    }

    public void unread(int id) {
        Api.unreadFeedbackMsg(id)
                .error((message, e) -> base.toast(message))
//                .success(this::requestData)
                .run();
    }
}

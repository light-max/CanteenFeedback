package com.pky.canteen.ui.opinion;

import android.os.Bundle;

import com.pky.canteen.api.Api;
import com.pky.canteen.base.call.Base;
import com.pky.canteen.base.mvp.BaseModel;

public class OpinionModel extends BaseModel<OpinionActivity> {
    @Override
    public void onCreate(Base base, Bundle savedInstanceState) {
        super.onCreate(base, savedInstanceState);
        requestData();
    }

    public void requestData() {
        Api.getOpinionAll()
                .error((message, e) -> base.toast(message))
                .success(data -> {
                    if (data.isEmpty()) {
                        base.toast("没有内容");
                    } else {
                        base.getView().getAdapter().setNewData(data);
                    }
                }).run();
    }
}

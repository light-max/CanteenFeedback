package com.pky.canteen.ui.collect;

import android.os.Bundle;

import com.pky.canteen.api.Api;
import com.pky.canteen.base.call.Base;
import com.pky.canteen.base.mvp.BaseModel;

public class CollectModel extends BaseModel<CollectActivity> {
    @Override
    public void onCreate(Base base, Bundle savedInstanceState) {
        super.onCreate(base, savedInstanceState);
        requestData();
    }

    public void requestData(){
        Api.getCollectAll()
                .error((message, e) -> base.toast(message))
                .success((data) -> {
                    if (data.isEmpty()) {
                        base.toast("没有收藏内容");
                    } else {
                        this.base.getView().getAdapter().setNewData(data);
                    }
                }).run();
    }

    public void unCollect(int dishId) {
        Api.postCollect(dishId)
                .error((message, e) -> base.toast(message))
                .success(this::requestData)
                .run();
    }
}

package com.pky.canteen.ui.stall.details;

import android.os.Bundle;

import com.pky.canteen.api.Api;
import com.pky.canteen.base.call.Base;
import com.pky.canteen.base.mvp.BaseModel;

public class StallDetailsModel extends BaseModel<StallDetailsFragment> {
    @Override
    public void onViewCreated(Base base, Bundle savedInstanceState) {
        super.onViewCreated(base, savedInstanceState);
        Api.getStallDetails(base.map("id"))
                .success(data -> this.base.getV().setData(data))
                .error((message, e) -> base.toast(message))
                .run();
    }
}

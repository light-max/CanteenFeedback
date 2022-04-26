package com.pky.canteen.ui.stall.dishlist;

import android.os.Bundle;

import com.pky.canteen.api.Api;
import com.pky.canteen.async.Async;
import com.pky.canteen.base.call.Base;
import com.pky.canteen.base.mvp.BaseModel;
import com.pky.canteen.data.result.DishDetailsPager;

public class StallDishModel extends BaseModel<StallDishFragment> {
    private int stallId;

    @Override
    public void onCreate(Base base, Bundle savedInstanceState) {
        super.onCreate(base, savedInstanceState);
        stallId = base.map("id");
    }

    public Async.Builder<DishDetailsPager> requestNewData() {
        return Api.getDishListByStallId(1, stallId)
                .error((message, e) -> base.toast(message))
                .success(data -> {
                    int nextPage = data.getPager().getCurrentPage() + 1;
                    base.map("next-page", nextPage);
                });
    }

    public Async.Builder<DishDetailsPager> requestNextData() {
        return Api.getDishListByStallId(base.map("next-page"),stallId)
                .error((message, e) -> base.toast(message))
                .success(data -> {
                    int nextPage = data.getPager().getCurrentPage() + 1;
                    base.map("next-page", nextPage);
                });
    }
}

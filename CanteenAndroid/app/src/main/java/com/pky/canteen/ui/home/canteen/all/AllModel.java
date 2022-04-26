package com.pky.canteen.ui.home.canteen.all;

import com.pky.canteen.api.Api;
import com.pky.canteen.async.Async;
import com.pky.canteen.base.mvp.BaseModel;
import com.pky.canteen.data.result.DishDetailsPager;

public class AllModel extends BaseModel<AllFragment> {
    public Async.Builder<DishDetailsPager> requestNewData() {
        return Api.getDishList(1)
                .error((message, e) -> base.toast(message))
                .success((data) -> {
                    int nextPage = data.getPager().getCurrentPage() + 1;
                    base.map("next-page", nextPage);
                });
    }

    public Async.Builder<DishDetailsPager> requestNextData() {
        return Api.getDishList(base.map("next-page"))
                .error((message, e) -> base.toast(message))
                .success(data -> {
                    int nextPage = data.getPager().getCurrentPage() + 1;
                    base.map("next-page", nextPage);
                });
    }
}

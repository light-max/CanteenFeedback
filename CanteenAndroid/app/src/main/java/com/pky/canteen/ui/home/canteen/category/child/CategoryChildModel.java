package com.pky.canteen.ui.home.canteen.category.child;

import android.os.Bundle;

import com.pky.canteen.api.Api;
import com.pky.canteen.async.Async;
import com.pky.canteen.base.call.Base;
import com.pky.canteen.base.mvp.BaseModel;
import com.pky.canteen.data.result.DishDetailsPager;

public class CategoryChildModel extends BaseModel<CategoryChildFragment> {
    private int cuisineId;

    @Override
    public void onCreate(Base base, Bundle savedInstanceState) {
        super.onCreate(base, savedInstanceState);
        if (savedInstanceState != null) {
            base.map("cuisineId", savedInstanceState.getInt("cuisineId"));
        }
        cuisineId = base.map("cuisineId");
    }

    public Async.Builder<DishDetailsPager> requestNewData() {
        return Api.getDishListByCuisine(1, cuisineId)
                .error((message, e) -> base.toast(message))
                .success(data -> {
                    int nextPage = data.getPager().getCurrentPage() + 1;
                    base.map("next-page", nextPage);
                });
    }

    public Async.Builder<DishDetailsPager> requestNextData() {
        return Api.getDishListByCuisine(base.map("next-page"), cuisineId)
                .error((message, e) -> base.toast(message))
                .success(data -> {
                    int nextPage = data.getPager().getCurrentPage() + 1;
                    base.map("next-page", nextPage);
                });
    }
}

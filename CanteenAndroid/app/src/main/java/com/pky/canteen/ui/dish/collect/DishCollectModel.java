package com.pky.canteen.ui.dish.collect;

import com.pky.canteen.api.Api;
import com.pky.canteen.async.Async;
import com.pky.canteen.base.mvp.BaseModel;
import com.pky.canteen.data.result.CollectorPager;

public class DishCollectModel extends BaseModel<DishCollectFragment> {
    public Async.Builder<CollectorPager> requestNewData() {
        return Api.getCollector(1, base.map("id"))
                .success(data -> {
                    int page = data.getPager().getCurrentPage() + 1;
                    base.map("next-page", page);
                }).error((message, e) -> base.toast(message));
    }

    public Async.Builder<CollectorPager> requestNextData() {
        return Api.getCollector(base.map("next-page"), base.map("id"))
                .success(data -> {
                    int page = data.getPager().getCurrentPage() + 1;
                    base.map("next-page", page);
                }).error((message, e) -> base.toast(message));
    }

    public void postCollect() {
        Api.postCollect(base.map("id"))
                .error((message, e) -> base.toast(message))
                .success(data -> {
                    base.getV().setCollectState(data);
                    base.requestCollectorCount();
                    base.requestNewData();
                }).run();
    }
}

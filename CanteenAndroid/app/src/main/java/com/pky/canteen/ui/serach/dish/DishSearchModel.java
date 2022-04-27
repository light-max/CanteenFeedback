package com.pky.canteen.ui.serach.dish;

import android.view.View;

import com.pky.canteen.api.Api;
import com.pky.canteen.base.mvp.BaseModel;
import com.pky.canteen.base.recycler.OnLoadMoreListener;
import com.pky.canteen.data.result.DishDetails;
import com.pky.canteen.ui.serach.OnSearchCall;

import java.util.List;

public class DishSearchModel extends BaseModel<DishSearchFragment> implements OnSearchCall {
    @Override
    public void onSearch(String value) {
        DishSearchView view = base.getV();
        Api.searchDish(0, value)
                .error((message, e) -> base.toast(message))
                .success(data -> {
                    List<DishDetails> list = data.getList();
                    view.getEmpty().setVisibility(list.isEmpty() ? View.VISIBLE : View.GONE);
                    view.getRecycler().setVisibility(list.isEmpty() ? View.GONE : View.VISIBLE);
                    view.getAdapter().setNewData(list);
                    int nextPage = data.getPager().getCurrentPage() + 1;
                    base.map("next-page", nextPage);
                }).run();
        view.getRecycler().addOnScrollListener(new OnLoadMoreListener(listener -> {
            Api.searchDish(base.map("next-page"), value)
                    .after(() -> listener.setLoadMoreIng(false))
                    .error((message, e) -> base.toast(message))
                    .success(data -> {
                        if (data.getList().isEmpty()) {
                            base.toast("没有更多了");
                        } else {
                            view.getAdapter().addNewData(data.getList());
                            int nextPage = data.getPager().getCurrentPage() + 1;
                            base.map("next-page", nextPage);
                        }
                    }).run();
        }));
    }
}

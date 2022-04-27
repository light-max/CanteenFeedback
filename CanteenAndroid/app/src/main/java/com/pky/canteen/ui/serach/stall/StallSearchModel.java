package com.pky.canteen.ui.serach.stall;

import android.view.View;

import com.pky.canteen.api.Api;
import com.pky.canteen.base.mvp.BaseModel;
import com.pky.canteen.ui.serach.OnSearchCall;

public class StallSearchModel extends BaseModel<StallSearchFragment> implements OnSearchCall {
    @Override
    public void onSearch(String value) {
        Api.searchStall(value)
                .error((message, e) -> base.toast(message))
                .success(data -> {
                    StallSearchView view = base.getV();
                    view.getEmpty().setVisibility(data.isEmpty() ? View.VISIBLE : View.GONE);
                    view.getRecycler().setVisibility(data.isEmpty() ? View.GONE : View.VISIBLE);
                    view.getAdapter().setNewData(data);
                }).run();
    }
}

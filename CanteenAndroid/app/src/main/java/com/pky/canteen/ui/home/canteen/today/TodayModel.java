package com.pky.canteen.ui.home.canteen.today;

import androidx.lifecycle.MutableLiveData;

import com.pky.canteen.api.Api;
import com.pky.canteen.async.Async;
import com.pky.canteen.base.mvp.BaseModel;
import com.pky.canteen.data.result.MenuItem;

import java.util.List;

public class TodayModel extends BaseModel<TodayFragment> {
    private final MutableLiveData<List<MenuItem>> newData = new MutableLiveData<>();
    private final MutableLiveData<List<MenuItem>> nextData = new MutableLiveData<>();

    public void requestNewData() {
        Api.getTodayMenu(1).success(data -> {
            int nextPage = data.getPager().getCurrentPage() + 1;
            base.map("next-page", nextPage);
            getNewData().postValue(data.getList());
        }).run();
    }

    public Async.Builder<?> requestNextData() {
        return Api.getTodayMenu(base.map("next-page")).success(data -> {
            int nextPage = data.getPager().getCurrentPage() + 1;
            base.map("next-page", nextPage);
            getNextData().postValue(data.getList());
        });
    }

    public MutableLiveData<List<MenuItem>> getNewData() {
        return newData;
    }

    public MutableLiveData<List<MenuItem>> getNextData() {
        return nextData;
    }
}

package com.pky.canteen.ui.dish.details;

import android.os.Bundle;

import androidx.lifecycle.MutableLiveData;

import com.pky.canteen.api.Api;
import com.pky.canteen.base.call.Base;
import com.pky.canteen.base.mvp.BaseModel;
import com.pky.canteen.data.result.DishDetails;

public class DishDetailsModel extends BaseModel<DishDetailsFragment> {
    private final MutableLiveData<DishDetails> data = new MutableLiveData<>();

    @Override
    public void onCreate(Base base, Bundle savedInstanceState) {
        super.onCreate(base, savedInstanceState);
        Api.getDishDetails(base.map("id"))
                .success(data -> getData().postValue(data))
                .error((message, e) -> base.toast(message))
                .run();
    }

    public MutableLiveData<DishDetails> getData() {
        return data;
    }
}

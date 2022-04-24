package com.pky.canteen.ui.dish.collect;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pky.canteen.R;
import com.pky.canteen.api.Api;
import com.pky.canteen.base.fragment.BaseFragment;
import com.pky.canteen.base.recycler.OnLoadMoreListener;

public class DishCollectFragment extends BaseFragment<DishCollectModel, DishCollectView> {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dish_collect, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requestNewData();
        requestCollectorCount();

        Api.checkCollect(map("id")).success(data -> {
            getV().setCollectState(data);
        }).run();

        getV().getCollect().setOnClickListener(v -> {
            getModel().postCollect();
        });
    }

    @SuppressLint("SetTextI18n")
    public void requestCollectorCount(){
        Api.getCollectorCount(map("id")).success(data -> {
            getV().getCount().setText("共" + data + "个收藏");
        }).run();
    }

    public void requestNewData(){
        getModel().requestNewData().success(data -> {
            getV().getAdapter().setNewData(data.getList());

            getV().getRecycler().addOnScrollListener(new OnLoadMoreListener(listener -> {
                getModel().requestNextData().success(data1 -> {
                    if (data1.getList().isEmpty()) {
                        toast("没有更多了");
                    } else {
                        getV().getAdapter().addNewData(data1.getList());
                    }
                }).run();
            }));
        }).run();
    }
}

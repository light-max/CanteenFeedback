package com.pky.canteen.ui.dish.remark;

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

public class DishRemarkFragment extends BaseFragment<DishRemarkModel, DishRemarkView> {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dish_remark, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requestRemark();
    }

    @SuppressLint("SetTextI18n")
    public void requestRemark() {
        Api.getRemarkCount(map("id")).success(data -> {
            getV().getCount().setText("共" + data + "条评论");
        }).run();

        getModel().requestNewData().success(data -> {
            getV().getAdapter().setNewData(data.getList());
            getV().getRecycler().setVisibility(data.getList().isEmpty() ? View.INVISIBLE : View.VISIBLE);
            getV().getEmpty().setVisibility(data.getList().isEmpty() ? View.VISIBLE : View.GONE);

            getV().getRecycler().addOnScrollListener(new OnLoadMoreListener(listener -> {
                getModel().requestNextData().after(() -> {
                    listener.setLoadMoreIng(false);
                }).success(data2 -> {
                    if (data2.getList().isEmpty()) {
                        toast("没有更多了");
                    } else {
                        getV().getAdapter().addNewData(data2.getList());
                    }
                }).run();
            }));
        }).run();
    }

    public void requestReply(int parentId) {
        getModel().requestReplyNewData(parentId).success(data -> {
            getV().getRAdapter().setNewData(data.getList());

            getV().getRRecycler().addOnScrollListener(new OnLoadMoreListener(listener -> {
                getModel().requestReplyNextData(parentId).after(() -> {
                    listener.setLoadMoreIng(false);
                }).success(data1 -> {
                    if (data1.getList().isEmpty()) {
                        toast("没有更多了");
                    } else {
                        getV().getRAdapter().addNewData(data1.getList());
                    }
                }).run();
            }));
        }).run();
    }
}

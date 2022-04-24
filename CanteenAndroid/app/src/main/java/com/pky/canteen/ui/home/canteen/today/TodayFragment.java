package com.pky.canteen.ui.home.canteen.today;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pky.canteen.R;
import com.pky.canteen.base.fragment.BaseFragment;
import com.pky.canteen.base.recycler.OnLoadMoreListener;
import com.pky.canteen.ui.dish.DishActivity;

public class TodayFragment extends BaseFragment<TodayModel, TodayView> {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_canteen_today, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getModel().requestNewData();
        getModel().getNewData().observe(this, menuItems -> {
            getV().getAdapter().setNewData(menuItems);
            getV().getRecycler().setVisibility(menuItems.isEmpty() ? View.GONE : View.VISIBLE);
            getV().getEmpty().setVisibility(menuItems.isEmpty() ? View.VISIBLE : View.GONE);
        });
        getModel().getNextData().observe(this, menuItems -> {
            if (menuItems.isEmpty()) {
                toast("没有更多了");
            } else {
                getV().getAdapter().addNewData(menuItems);
            }
        });
        getV().getRecycler().addOnScrollListener(new OnLoadMoreListener(listener -> {
            getModel().requestNextData().after(() -> {
                listener.setLoadMoreIng(false);
            }).run();
        }));
        getV().getAdapter().setOnItemClickListener((data, position) -> {
            DishActivity.start(getContext(), data.getDishId());
        });
    }
}

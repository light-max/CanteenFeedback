package com.pky.canteen.ui.stall.dishlist;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.pky.canteen.R;
import com.pky.canteen.base.call.Base;
import com.pky.canteen.base.mvp.BaseView;
import com.pky.canteen.ui.dish.DishActivity;

public class StallDishView extends BaseView<StallDishFragment> {
    private RecyclerView recycler;
    private DishListAdapter adapter;

    @Override
    public void onViewCreated(Base base, Bundle savedInstanceState) {
        super.onViewCreated(base, savedInstanceState);
        recycler = get(R.id.recycler);
        adapter = new DishListAdapter();
        recycler.setAdapter(adapter);
        adapter.setOnItemClickListener((data, position) -> {
            DishActivity.startHideStall(base.getContext(), data.getId());
        });
    }

    public RecyclerView getRecycler() {
        return recycler;
    }

    public DishListAdapter getAdapter() {
        return adapter;
    }
}

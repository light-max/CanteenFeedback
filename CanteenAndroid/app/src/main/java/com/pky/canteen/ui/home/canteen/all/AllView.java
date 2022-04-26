package com.pky.canteen.ui.home.canteen.all;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.pky.canteen.R;
import com.pky.canteen.base.call.Base;
import com.pky.canteen.base.mvp.BaseView;
import com.pky.canteen.ui.dish.DishActivity;

public class AllView extends BaseView<AllFragment> {
    private RecyclerView recycler;
    private DishAdapter adapter;

    @Override
    public void onViewCreated(Base base, Bundle savedInstanceState) {
        super.onViewCreated(base, savedInstanceState);
        recycler = get(R.id.recycler);
        adapter = new DishAdapter();
        recycler.setAdapter(adapter);
        adapter.setOnItemClickListener((data, position) -> {
            DishActivity.start(base.getContext(), data.getId());
        });
    }

    public RecyclerView getRecycler() {
        return recycler;
    }

    public DishAdapter getAdapter() {
        return adapter;
    }
}

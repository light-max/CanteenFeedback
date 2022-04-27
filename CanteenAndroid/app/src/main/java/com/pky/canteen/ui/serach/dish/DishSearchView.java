package com.pky.canteen.ui.serach.dish;

import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pky.canteen.R;
import com.pky.canteen.base.call.Base;
import com.pky.canteen.base.mvp.BaseView;
import com.pky.canteen.ui.dish.DishActivity;

public class DishSearchView extends BaseView<DishSearchFragment> {
    private RecyclerView recycler;
    private DishListAdapter adapter;
    private TextView empty;

    @Override
    public void onViewCreated(Base base, Bundle savedInstanceState) {
        super.onViewCreated(base, savedInstanceState);
        recycler = get(R.id.recycler);
        empty = get(R.id.empty);
        adapter = new DishListAdapter();
        recycler.setAdapter(adapter);
        adapter.setOnItemClickListener((data, position) -> {
            DishActivity.start(base.getContext(), data.getId());
        });
    }

    public RecyclerView getRecycler() {
        return recycler;
    }

    public DishListAdapter getAdapter() {
        return adapter;
    }

    public TextView getEmpty() {
        return empty;
    }
}

package com.pky.canteen.ui.collect;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.pky.canteen.R;
import com.pky.canteen.base.call.Base;
import com.pky.canteen.base.mvp.BaseView;
import com.pky.canteen.ui.dish.DishActivity;

public class CollectView extends BaseView<CollectActivity> {
    private RecyclerView recycler;
    private DishListAdapter adapter;

    @Override
    public void onCreate(Base base, Bundle savedInstanceState) {
        super.onCreate(base, savedInstanceState);
        click(R.id.back, () -> base.getActivity().finish());
        recycler = get(R.id.recycler);
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
}

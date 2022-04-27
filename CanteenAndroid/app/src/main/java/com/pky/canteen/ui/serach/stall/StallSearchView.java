package com.pky.canteen.ui.serach.stall;

import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pky.canteen.R;
import com.pky.canteen.base.call.Base;
import com.pky.canteen.base.mvp.BaseView;
import com.pky.canteen.ui.stall.StallActivity;

public class StallSearchView extends BaseView<StallSearchFragment> {
    private RecyclerView recycler;
    private StallListAdapter adapter;
    private TextView empty;

    @Override
    public void onViewCreated(Base base, Bundle savedInstanceState) {
        super.onViewCreated(base, savedInstanceState);
        recycler = get(R.id.recycler);
        empty = get(R.id.empty);
        adapter = new StallListAdapter();
        recycler.setAdapter(adapter);
        adapter.setOnItemClickListener((data, position) -> {
            StallActivity.start(base.getContext(), data.getId());
        });
    }

    public RecyclerView getRecycler() {
        return recycler;
    }

    public StallListAdapter getAdapter() {
        return adapter;
    }

    public TextView getEmpty() {
        return empty;
    }
}

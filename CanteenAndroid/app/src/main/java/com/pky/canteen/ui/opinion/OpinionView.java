package com.pky.canteen.ui.opinion;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.pky.canteen.R;
import com.pky.canteen.base.call.Base;
import com.pky.canteen.base.mvp.BaseView;
import com.pky.canteen.ui.opiniondetails.OpinionDetailsActivity;

public class OpinionView extends BaseView<OpinionActivity> {
    private RecyclerView recycler;
    private OpinionListAdapter adapter;

    @Override
    public void onCreate(Base base, Bundle savedInstanceState) {
        super.onCreate(base, savedInstanceState);
        click(R.id.back, () -> base.getActivity().finish());
        recycler = get(R.id.recycler);
        adapter = new OpinionListAdapter();
        recycler.setAdapter(adapter);
        adapter.setOnItemClickListener((data, position) -> {
            OpinionDetailsActivity.start(base.getContext(), data.getId());
        });
    }

    public RecyclerView getRecycler() {
        return recycler;
    }

    public OpinionListAdapter getAdapter() {
        return adapter;
    }
}

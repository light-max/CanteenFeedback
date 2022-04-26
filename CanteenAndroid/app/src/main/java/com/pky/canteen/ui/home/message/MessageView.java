package com.pky.canteen.ui.home.message;

import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.pky.canteen.R;
import com.pky.canteen.base.call.Base;
import com.pky.canteen.base.mvp.BaseView;

public class MessageView extends BaseView<MessageFragment> {
    private TextView empty;
    private RecyclerView recycler;
    private MsgAdapter adapter;
    private SwipeRefreshLayout swipe;

    @Override
    public void onViewCreated(Base base, Bundle savedInstanceState) {
        super.onViewCreated(base, savedInstanceState);
        empty = get(R.id.empty);
        recycler = get(R.id.recycler);
        swipe = get(R.id.swipe);
        adapter = new MsgAdapter();
        recycler.setAdapter(adapter);
        swipe.setOnRefreshListener(() -> {
            this.base.getModel().requestData();
            swipe.setRefreshing(false);
        });
    }

    public TextView getEmpty() {
        return empty;
    }

    public RecyclerView getRecycler() {
        return recycler;
    }

    public MsgAdapter getAdapter() {
        return adapter;
    }

    public SwipeRefreshLayout getSwipe() {
        return swipe;
    }
}

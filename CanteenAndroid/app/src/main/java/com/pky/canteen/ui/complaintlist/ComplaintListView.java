package com.pky.canteen.ui.complaintlist;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.pky.canteen.R;
import com.pky.canteen.base.call.Base;
import com.pky.canteen.base.mvp.BaseView;
import com.pky.canteen.ui.complaint.ComplaintActivity;
import com.pky.canteen.ui.opiniondetails.OpinionDetailsActivity;

public class ComplaintListView extends BaseView<ComplaintListActivity> {
    private RecyclerView recycler;
    private ComplaintListAdapter adapter;

    @Override
    public void onCreate(Base base, Bundle savedInstanceState) {
        super.onCreate(base, savedInstanceState);
        click(R.id.back, () -> base.getActivity().finish());
        recycler = get(R.id.recycler);
        adapter = new ComplaintListAdapter();
        recycler.setAdapter(adapter);
        adapter.setOnItemClickListener((data, position) -> {
            View view = View.inflate(
                    base.getContext(),
                    R.layout.view_dlg_complaint_open_model,
                    null
            );
            AlertDialog dialog = new AlertDialog.Builder(base.getContext())
                    .setView(view)
                    .show();
            view.findViewById(R.id.opinion).setOnClickListener(v -> {
                OpinionDetailsActivity.start(base.getContext(), data.getOpinionId());
                dialog.dismiss();
            });
            view.findViewById(R.id.complaint).setOnClickListener(v -> {
                ComplaintActivity.start(base.getContext(), data.getId());
                dialog.dismiss();
            });
        });
    }

    public RecyclerView getRecycler() {
        return recycler;
    }

    public ComplaintListAdapter getAdapter() {
        return adapter;
    }
}

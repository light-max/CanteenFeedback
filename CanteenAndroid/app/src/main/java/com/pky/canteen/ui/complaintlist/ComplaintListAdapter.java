package com.pky.canteen.ui.complaintlist;

import com.pky.canteen.R;
import com.pky.canteen.base.recycler.SimpleSingleItemRecyclerAdapter;
import com.pky.canteen.data.result.ComplaintDetails;

class ComplaintListAdapter extends SimpleSingleItemRecyclerAdapter<ComplaintDetails> {
    @Override
    protected int getItemViewLayout() {
        return R.layout.item_complaint;
    }

    @Override
    protected void onBindViewHolder(ViewHolder holder, ComplaintDetails data, int position) {
        holder.setText(R.id.feedback, "食堂：" + data.getFeedbackContent())
                .setText(R.id.complaint, "我：" + data.getDes())
                .setText(R.id.time, data.getCreateTime());
    }
}

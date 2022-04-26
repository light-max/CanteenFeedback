package com.pky.canteen.ui.home.message;

import android.view.View;

import com.pky.canteen.R;
import com.pky.canteen.base.recycler.SimpleSingleItemRecyclerAdapter;
import com.pky.canteen.data.result.FeedbackMsg;

class MsgAdapter extends SimpleSingleItemRecyclerAdapter<FeedbackMsg> {
    @Override
    protected int getItemViewLayout() {
        return R.layout.item_message_feedback_msg;
    }

    @Override
    protected void onBindViewHolder(ViewHolder holder, FeedbackMsg data, int position) {
        holder.setText(R.id.opinion, "建议：" + data.getOpinionContent())
                .setText(R.id.feedback, "回复：" + data.getFeedbackContent())
                .setText(R.id.time, data.getCreateTimeText());
        holder.get(R.id.no_reading).setVisibility(data.getReading() ? View.GONE : View.VISIBLE);
    }
}

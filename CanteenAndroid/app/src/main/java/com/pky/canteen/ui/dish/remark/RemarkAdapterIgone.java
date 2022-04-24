package com.pky.canteen.ui.dish.remark;

import android.annotation.SuppressLint;

import com.pky.canteen.R;
import com.pky.canteen.base.recycler.SimpleSingleItemRecyclerAdapter;
import com.pky.canteen.data.result.Remark;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 弃用
 */
@Deprecated
class RemarkAdapterIgone extends SimpleSingleItemRecyclerAdapter<Remark> {
    private Map<Integer, List<Remark>> replyMap = new HashMap<>();

    @Override
    protected int getItemViewLayout() {
        return R.layout.item_dish_details_remark;
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void onBindViewHolder(ViewHolder holder, Remark data, int position) {
        holder.setText(R.id.content, data.getContent());
//        TextView reply = holder.getText(R.id.reply);
//        if (data.getReplyCount() == 0) {
//            holder.get(R.id.reply_view).setVisibility(View.GONE);
//        } else {
//            holder.get(R.id.reply_view).setVisibility(View.VISIBLE);
//            reply.setText(String.format("展开剩余%d条回复", data.getReplyCount()));
//        }
//        RecyclerView recycler = holder.get(R.id.recycler);
//        ChildAdapter adapter = new ChildAdapter();
//        recycler.setAdapter(adapter);
//        if (replyMap.containsKey(data.getId())) {
//            adapter.setNewData(replyMap.get(data.getId()));
//            reply.setText("收起");
//        } else {
//            adapter.getData().clear();
//            adapter.notifyDataSetChanged();
//        }
//        holder.get(R.id.reply_view).setOnClickListener(v -> {
//            List<Remark> remarks = replyMap.get(data.getId());
//            if (remarks == null) {
//                Api.getReplyList(1, data.getId()).success(pager -> {
//                    replyMap.put(data.getId(), pager.getList());
//                    notifyItemChanged(position);
//                }).run();
//            } else {
//                replyMap.remove(data.getId());
//                notifyItemChanged(position);
//            }
//        });
    }
}

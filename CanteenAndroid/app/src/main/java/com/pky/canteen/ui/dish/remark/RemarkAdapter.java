package com.pky.canteen.ui.dish.remark;

import android.annotation.SuppressLint;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.pky.canteen.R;
import com.pky.canteen.api.ExRequestBuilder;
import com.pky.canteen.base.recycler.SimpleSingleItemRecyclerAdapter;
import com.pky.canteen.data.result.Remark;

class RemarkAdapter extends SimpleSingleItemRecyclerAdapter<Remark> {
    private OnShowReplyListener onShowReplyListener;
    private OnReplyRemarkListener onReplyRemarkListener;

    @Override
    protected int getItemViewLayout() {
        return R.layout.item_dish_details_remark;
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void onBindViewHolder(ViewHolder holder, Remark data, int position) {
        Glide.with(holder.itemView)
                .load(ExRequestBuilder.getUrl(data.getCreateByHead()))
                .apply(new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(holder.getImage(R.id.head));
        holder.setText(R.id.content, data.getContent().replace("\n", ""))
                .setText(R.id.name, data.getCreateByName())
                .setText(R.id.uid, data.getCreateById())
                .setText(R.id.time, data.getCreateTimeText());
        if (data.getReplyCount() > 0) {
            holder.setText(R.id.show_reply, String.format("共%d条回复，点击查看", data.getReplyCount()));
            holder.get(R.id.show_reply).setVisibility(View.VISIBLE);
        } else {
            holder.get(R.id.show_reply).setVisibility(View.GONE);
        }
        holder.click(R.id.show_reply, () -> {
            if (onShowReplyListener != null) {
                onShowReplyListener.onShowReply(data, position);
            }
        });
        holder.click(R.id.reply, () -> {
            if (onReplyRemarkListener != null) {
                onReplyRemarkListener.onReplyRemark(data, position);
            }
        });
    }

    public interface OnShowReplyListener {
        void onShowReply(Remark data, int position);
    }

    public interface OnReplyRemarkListener {
        void onReplyRemark(Remark data, int position);
    }

    public void setOnShowReplyListener(OnShowReplyListener onShowReplyListener) {
        this.onShowReplyListener = onShowReplyListener;
    }

    public void setOnReplyRemarkListener(OnReplyRemarkListener onReplyRemarkListener) {
        this.onReplyRemarkListener = onReplyRemarkListener;
    }
}

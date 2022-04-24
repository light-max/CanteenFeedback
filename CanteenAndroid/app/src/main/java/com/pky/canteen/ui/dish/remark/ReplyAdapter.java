package com.pky.canteen.ui.dish.remark;

import android.annotation.SuppressLint;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.pky.canteen.R;
import com.pky.canteen.api.ExRequestBuilder;
import com.pky.canteen.base.recycler.SimpleSingleItemRecyclerAdapter;
import com.pky.canteen.data.result.Remark;

class ReplyAdapter extends SimpleSingleItemRecyclerAdapter<Remark> {
    @Override
    protected int getItemViewLayout() {
        return R.layout.item_dish_details_reply;
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
    }
}

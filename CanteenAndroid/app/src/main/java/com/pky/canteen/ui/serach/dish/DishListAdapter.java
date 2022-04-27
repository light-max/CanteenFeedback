package com.pky.canteen.ui.serach.dish;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.pky.canteen.R;
import com.pky.canteen.api.ExRequestBuilder;
import com.pky.canteen.base.recycler.SimpleSingleItemRecyclerAdapter;
import com.pky.canteen.data.result.DishDetails;

class DishListAdapter extends SimpleSingleItemRecyclerAdapter<DishDetails> {
    @Override
    protected int getItemViewLayout() {
        return R.layout.item_search_result;
    }

    @Override
    protected void onBindViewHolder(ViewHolder holder, DishDetails data, int position) {
        Glide.with(holder.itemView)
                .load(ExRequestBuilder.getUrl(data.getCover()))
                .apply(new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(holder.getImage(R.id.cover));
        holder.setText(R.id.name, data.getName())
                .setText(R.id.des, data.getDes());
    }
}

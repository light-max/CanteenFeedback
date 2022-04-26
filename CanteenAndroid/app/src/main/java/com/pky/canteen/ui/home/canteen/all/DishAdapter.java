package com.pky.canteen.ui.home.canteen.all;

import com.bumptech.glide.Glide;
import com.pky.canteen.R;
import com.pky.canteen.api.ExRequestBuilder;
import com.pky.canteen.base.recycler.SimpleSingleItemRecyclerAdapter;
import com.pky.canteen.data.result.DishDetails;

class DishAdapter extends SimpleSingleItemRecyclerAdapter<DishDetails> {
    @Override
    protected int getItemViewLayout() {
        return R.layout.item_canteen_all_dish;
    }

    @Override
    protected void onBindViewHolder(ViewHolder holder, DishDetails data, int position) {
        Glide.with(holder.itemView)
                .load(ExRequestBuilder.getUrl(data.getCover()))
                .into(holder.getImage(R.id.cover));
        holder.setText(R.id.name, data.getName())
                .setText(R.id.cuisine, data.getCuisine())
                .setText(R.id.material, "原材料：" + data.getMaterial())
                .setText(R.id.des, data.getDes());
    }
}

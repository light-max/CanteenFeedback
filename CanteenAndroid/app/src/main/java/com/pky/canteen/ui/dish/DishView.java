package com.pky.canteen.ui.dish;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.pky.canteen.R;
import com.pky.canteen.base.call.Base;
import com.pky.canteen.base.fragment.BaseFragment;
import com.pky.canteen.base.mvp.BaseView;
import com.pky.canteen.ui.dish.collect.DishCollectFragment;
import com.pky.canteen.ui.dish.details.DishDetailsFragment;
import com.pky.canteen.ui.dish.remark.DishRemarkFragment;

public class DishView extends BaseView<DishActivity> {
    @Override
    public void onCreate(Base base, Bundle savedInstanceState) {
        super.onCreate(base, savedInstanceState);
        if (savedInstanceState != null) {
            base.map("id", savedInstanceState.getInt("id", 1));
            base.map("hideStall", savedInstanceState.getBoolean("hideStall", false));
        }
        click(R.id.back, () -> base.getActivity().finish());
        TabLayout tab = get(R.id.tab);
        ViewPager pager = get(R.id.pager);
        pager.setOffscreenPageLimit(3);
        BaseFragment<?, ?>[] fragments = new BaseFragment[]{
                new DishDetailsFragment(),
                new DishRemarkFragment(),
                new DishCollectFragment()
        };
        int windowWidth = getWindowWidth();
        for (BaseFragment<?, ?> fragment : fragments) {
            fragment.map("id", base.map("id"));
            fragment.map("windowWidth", windowWidth);
        }
        fragments[0].map("hideStall", base.map("hideStall"));
        FragmentManager manager = base.getActivity().getSupportFragmentManager();
        pager.setAdapter(new FragmentPagerAdapter(manager, 0) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return new String[]{
                        "菜品", "评论", "收藏"
                }[position];
            }
        });
        tab.setupWithViewPager(pager);
    }

    private int getWindowWidth() {
        return base.getActivity()
                .getWindowManager()
                .getDefaultDisplay()
                .getWidth();
    }
}

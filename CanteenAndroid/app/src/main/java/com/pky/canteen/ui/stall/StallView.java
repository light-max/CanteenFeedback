package com.pky.canteen.ui.stall;

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
import com.pky.canteen.ui.stall.details.StallDetailsFragment;
import com.pky.canteen.ui.stall.dishlist.StallDishFragment;

public class StallView extends BaseView<StallActivity> {
    private BaseFragment<?, ?>[] fragments;

    @Override
    public void onCreate(Base base, Bundle savedInstanceState) {
        super.onCreate(base, savedInstanceState);
        click(R.id.back, () -> base.getActivity().finish());
        fragments = new BaseFragment[]{
                new StallDetailsFragment(),
                new StallDishFragment()
        };
        fragments[0].map("id", base.map("id"));
        fragments[1].map("id", base.map("id"));
        TabLayout tab = get(R.id.tab);
        ViewPager pager = get(R.id.pager);
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
                return new String[]{"详情", "菜品"}[position];
            }
        });
        tab.setupWithViewPager(pager);
    }
}

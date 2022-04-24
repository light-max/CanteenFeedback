package com.pky.canteen.ui.home.canteen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.pky.canteen.R;
import com.pky.canteen.base.call.Base;
import com.pky.canteen.base.mvp.BaseView;
import com.pky.canteen.ui.home.canteen.all.AllFragment;
import com.pky.canteen.ui.home.canteen.category.CategoryFragment;
import com.pky.canteen.ui.home.canteen.today.TodayFragment;

public class CanteenView extends BaseView<CanteenFragment> {
    private TabLayout tab;
    private ViewPager pager;
    private Fragment[] fragments;

    @Override
    public void onViewCreated(Base base, Bundle savedInstanceState) {
        tab = get(R.id.tab);
        pager = get(R.id.pager);
        fragments = new Fragment[]{
                new TodayFragment(),
                new AllFragment(),
                new CategoryFragment()
        };
        FragmentManager manager = base.getFragment().getChildFragmentManager();
        pager.setAdapter(new FragmentStatePagerAdapter(manager, 0) {
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
                        "今日菜品",
                        "所有",
                        "分类"
                }[position];
            }
        });
        tab.setupWithViewPager(pager);
    }
}

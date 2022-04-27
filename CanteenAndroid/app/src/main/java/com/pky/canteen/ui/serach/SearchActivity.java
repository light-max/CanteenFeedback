package com.pky.canteen.ui.serach;

import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.pky.canteen.R;
import com.pky.canteen.base.activity.NoMvpActivity;
import com.pky.canteen.base.fragment.BaseFragment;
import com.pky.canteen.ui.serach.dish.DishSearchFragment;
import com.pky.canteen.ui.serach.stall.StallSearchFragment;

public class SearchActivity extends NoMvpActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideActionBar();
        BaseFragment<?, ?>[] fragments = new BaseFragment[]{
                new DishSearchFragment(),
                new StallSearchFragment()
        };
        TabLayout tab = get(R.id.tab);
        ViewPager pager = get(R.id.pager);
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(), 0) {
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
                        "菜品",
                        "档口"
                }[position];
            }
        });
        tab.setupWithViewPager(pager);
        click(R.id.search, () -> {
            EditText text = get(R.id.value);
            String value = text.getText().toString();
            if (value.trim().isEmpty()) {
                toast("请输入搜索内容");
            } else {
                ((OnSearchCall) fragments[0]).onSearch(value);
                ((OnSearchCall) fragments[1]).onSearch(value);
            }
        });
    }
}

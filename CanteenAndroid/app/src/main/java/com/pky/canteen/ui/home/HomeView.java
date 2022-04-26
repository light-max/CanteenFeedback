package com.pky.canteen.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pky.canteen.R;
import com.pky.canteen.base.call.Base;
import com.pky.canteen.base.mvp.BaseView;
import com.pky.canteen.ui.home.canteen.CanteenFragment;
import com.pky.canteen.ui.home.message.MessageFragment;
import com.pky.canteen.ui.home.mine.MineFragment;

import org.jetbrains.annotations.NotNull;

public class HomeView extends BaseView<HomeActivity> {
    private ViewPager2 pager;
    private BottomNavigationView nav;
    private CanteenFragment canteenFragment;
    private MessageFragment messageFragment;
    private MineFragment mineFragment;

    @Override
    public void onCreate(Base base, Bundle savedInstanceState) {
        super.onCreate(base, savedInstanceState);
        pager = get(R.id.pager);
        nav = get(R.id.nav);
        canteenFragment = new CanteenFragment();
        messageFragment = new MessageFragment();
        mineFragment = new MineFragment();
        Fragment[] fragments = {
                canteenFragment,
                messageFragment,
                mineFragment
        };
        pager.setUserInputEnabled(false);
        pager.setAdapter(new FragmentStateAdapter(base.getActivity()) {
            @NonNull
            @NotNull
            @Override
            public Fragment createFragment(int position) {
                return fragments[position];
            }

            @Override
            public int getItemCount() {
                return fragments.length;
            }
        });
        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                nav.setSelectedItemId(new int[]{
                        R.id.canteen,
                        R.id.message,
                        R.id.mine
                }[position]);
            }
        });
        nav.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.canteen) {
                pager.setCurrentItem(0, false);
            } else if (item.getItemId() == R.id.message) {
                pager.setCurrentItem(1, false);
            } else if (item.getItemId() == R.id.mine) {
                pager.setCurrentItem(2, false);
            }
            return true;
        });
    }


}

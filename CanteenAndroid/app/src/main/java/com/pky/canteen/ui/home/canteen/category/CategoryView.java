package com.pky.canteen.ui.home.canteen.category;

import android.os.Bundle;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.pky.canteen.R;
import com.pky.canteen.base.call.Base;
import com.pky.canteen.base.mvp.BaseView;
import com.pky.canteen.data.result.Cuisine;
import com.pky.canteen.ui.home.canteen.category.child.CategoryChildFragment;

import java.util.HashMap;
import java.util.Map;

public class CategoryView extends BaseView<CategoryFragment> implements CuisineVerticalLayout.OnSelectedListener {
    private final Map<Integer, CategoryChildFragment> fragmentMap = new HashMap<>();
    private CategoryChildFragment currentFragment;
    private CuisineVerticalLayout cuisine;
    private TextView title;

    @Override
    public void onViewCreated(Base base, Bundle savedInstanceState) {
        super.onViewCreated(base, savedInstanceState);
        cuisine = get(R.id.cuisine);
        title = get(R.id.title);
        cuisine.requestData();
        cuisine.setOnSelectedListener(this);
    }

    @Override
    public void onSelected(Cuisine c, int position) {
        title.setText(c.getName());
        FragmentManager manager = base.getFragment().getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        CategoryChildFragment fragment = fragmentMap.get(c.getId());
        if (fragment == null) {
            fragment = new CategoryChildFragment();
            fragment.map("cuisineId", c.getId());
            fragmentMap.put(c.getId(), fragment);
        }
        if (fragment != currentFragment) {
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            if (fragment.isAdded()) {
                transaction.show(fragment);
            } else {
                transaction.add(R.id.container, fragment);
            }
            currentFragment = fragment;
            transaction.commit();
        }
    }
}

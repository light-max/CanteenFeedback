package com.pky.canteen.ui.home.canteen.category;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import com.pky.canteen.R;
import com.pky.canteen.api.Api;
import com.pky.canteen.data.result.Cuisine;
import com.pky.canteen.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

public class CuisineVerticalLayout extends ScrollView {
    private List<Cuisine> cuisines;
    private final RadioGroup rootView;
    private final int itemWidth;
    private final int itemHeight;
    private final float textSize;
    private final int dp4;
    private final int dp1;
    private List<RadioButton> buttons;

    private OnSelectedListener onSelectedListener;

    public CuisineVerticalLayout(Context context) {
        this(context, null);
    }

    public CuisineVerticalLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        itemWidth = DisplayUtils.dp2px(context, 88);
        itemHeight = DisplayUtils.dp2px(context, 48);
        textSize = 16;
        dp4 = DisplayUtils.dp2px(context, 4);
        dp1 = DisplayUtils.dp2px(context, 1);
        rootView = new RadioGroup(context);
        rootView.setOrientation(LinearLayout.VERTICAL);
        rootView.setLayoutParams(new RadioGroup.LayoutParams(
                RadioGroup.LayoutParams.WRAP_CONTENT,
                RadioGroup.LayoutParams.MATCH_PARENT
        ));
        rootView.setPadding(0, itemHeight / 2, 0, 0);
        addView(rootView);
    }

    @SuppressLint({"UseCompatLoadingForColorStateLists", "ResourceType"})
    public void updateUi() {
        rootView.removeAllViews();
        buttons = new ArrayList<>();
        for (int i = 0; i < cuisines.size(); i++) {
            Cuisine c = cuisines.get(i);
            RadioButton button = new RadioButton(getContext());
            button.setLayoutParams(new LinearLayout.LayoutParams(itemWidth, itemHeight));
            button.setButtonDrawable(null);
            button.setGravity(Gravity.CENTER);
//            button.setTypeface(Typeface.DEFAULT_BOLD);
            button.setTextSize(textSize);
            button.setText(c.getName());
            button.setTextColor(getResources().getColorStateList(R.drawable.color_category_item));
            rootView.addView(button);
            View line = new View(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, dp1);
            params.leftMargin = dp4;
            params.rightMargin = dp4;
            line.setLayoutParams(params);
            line.setBackgroundResource(android.R.color.darker_gray);
            rootView.addView(line);
            int finalI = i;
            button.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked && onSelectedListener != null) {
                    onSelectedListener.onSelected(c, finalI);
                }
            });
            buttons.add(button);
        }
    }

    public void requestData() {
        Api.getCuisineList().success(data -> {
            cuisines = data;
            updateUi();
            buttons.get(0).setChecked(true);
        }).run();
    }

    public void setOnSelectedListener(OnSelectedListener onSelectedListener) {
        this.onSelectedListener = onSelectedListener;
        if (cuisines != null) {
            buttons.get(0).setChecked(true);
        }
    }

    public interface OnSelectedListener {
        void onSelected(Cuisine c, int position);
    }
}

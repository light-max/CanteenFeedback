package com.pky.canteen.ui.opinionsend;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pky.canteen.R;
import com.pky.canteen.base.call.Base;
import com.pky.canteen.base.mvp.BaseView;

public class OpinionSendView extends BaseView<OpinionSendActivity> {
    private RecyclerView recycler;
    private ImageAdapter adapter;
    private AlertDialog dialog;
    private EditText content;

    @Override
    public void onCreate(Base base, Bundle savedInstanceState) {
        super.onCreate(base, savedInstanceState);
        click(R.id.back, () -> this.base.finish());
        click(R.id.add_image, () -> this.base.picture());
        click(R.id.add_video, () -> this.base.video());
        click(R.id.remove_video, () -> {
            this.base.getModel().setVideoPath(null);
            get(R.id.video_view).setVisibility(View.GONE);
        });
        click(R.id.post, () -> this.base.getModel().post());
        recycler = get(R.id.recycler);
        content = get(R.id.content);
        recycler.setLayoutManager(new GridLayoutManager(base.getContext(), 3));
        int width = base.getActivity()
                .getWindowManager()
                .getDefaultDisplay()
                .getWidth();
        adapter = new ImageAdapter(width / 3);
        recycler.setAdapter(adapter);
    }

    public RecyclerView getRecycler() {
        return recycler;
    }

    public ImageAdapter getAdapter() {
        return adapter;
    }

    public EditText getContent() {
        return content;
    }

    public void showDialog() {
        dialog = new AlertDialog.Builder(base.getContext())
                .setCancelable(false)
                .setView(new ProgressBar(base.getContext()))
                .show();
    }

    public void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
        dialog = null;
    }
}

package com.pky.canteen.ui.dish.remark;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pky.canteen.R;
import com.pky.canteen.base.call.Base;
import com.pky.canteen.base.mvp.BaseView;
import com.pky.canteen.data.result.Remark;

import java.util.Objects;

public class DishRemarkView extends BaseView<DishRemarkFragment> implements RemarkAdapter.OnShowReplyListener, RemarkAdapter.OnReplyRemarkListener {
    private RecyclerView recycler;
    private TextView count;
    private RemarkAdapter adapter;
    private TextView empty;

    private RecyclerView rRecycler;
    private TextView rCount;
    private ReplyAdapter rAdapter;

    @SuppressLint("CutPasteId")
    @Override
    public void onViewCreated(Base base, Bundle savedInstanceState) {
        recycler = get(R.id.recycler);
        View view = View.inflate(base.getContext(), R.layout.view_remark_count_text, null);
        count = view.findViewById(R.id.text);
        empty = get(R.id.empty);
        adapter = new RemarkAdapter();
        recycler.setAdapter(adapter);
        adapter.setOnShowReplyListener(this);
        adapter.setOnReplyRemarkListener(this);
        adapter.setHeadView(view);

        rRecycler = get(R.id.reply_recycler);
        view = View.inflate(base.getContext(), R.layout.view_reply_count_text, null);
        rAdapter = new ReplyAdapter();
        rRecycler.setAdapter(rAdapter);
        rAdapter.setHeadView(view);
        rCount = view.findViewById(R.id.text);
        view.findViewById(R.id.close).setOnClickListener(v -> {
            get(R.id.reply_view).startAnimation(AnimationUtils.loadAnimation(base.getContext(), R.anim.reply_layout_botton_out));
            get(R.id.reply_view).setVisibility(View.GONE);
        });

        click(R.id.send_remark, () -> {
            EditText remark = get(R.id.remark);
            String string = remark.getText().toString();
            if (!string.isEmpty()) {
                this.base.getModel()
                        .sendRemark(base.map("id"), string)
                        .error((message, e) -> base.toast(message))
                        .success(() -> {
                            this.base.requestRemark();
                            remark.setText("");
                            hideInput();
                        })
                        .run();
            }
        });
    }

    public RecyclerView getRecycler() {
        return recycler;
    }

    public TextView getCount() {
        return count;
    }

    public RemarkAdapter getAdapter() {
        return adapter;
    }

    public TextView getEmpty() {
        return empty;
    }

    public RecyclerView getRRecycler() {
        return rRecycler;
    }

    public ReplyAdapter getRAdapter() {
        return rAdapter;
    }

    public TextView getRCount() {
        return rCount;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onShowReply(Remark data, int position) {
        get(R.id.reply_view).startAnimation(AnimationUtils.loadAnimation(base.getContext(), R.anim.reply_layout_botton_in));
        get(R.id.reply_view).setVisibility(View.VISIBLE);
        base.requestReply(data.getId());
        getRCount().setText("共" + data.getReplyCount() + "条回复");
    }

    @Override
    public void onReplyRemark(Remark data, int position) {
        EditText text = new EditText(base.getContext());
        text.setHint("请输入回复内容");
        new AlertDialog.Builder(base.getContext())
                .setMessage("回复")
                .setView(text)
                .setNegativeButton("取消", null)
                .setPositiveButton("发送", (dialog, which) -> {
                    String string = text.getText().toString();
                    if (string.isEmpty()) {
                        base.toast("回复内容不能为空");
                    } else {
                        base.getModel()
                                .sendReply(base.map("id"), data.getId(), string)
                                .error((message, e) -> base.toast(message))
                                .success(() -> {
                                    data.setReplyCount(data.getReplyCount() + 1);
                                    adapter.notifyDataSetChanged();
                                }).run();
                    }
                }).show();
    }

    private void hideInput() {
        InputMethodManager imm = (InputMethodManager) Objects
                .requireNonNull(base.getContext())
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        // 隐藏软键盘
        imm.hideSoftInputFromWindow(Objects.requireNonNull(base.getActivity()).getWindow().getDecorView().getWindowToken(), 0);
    }
}

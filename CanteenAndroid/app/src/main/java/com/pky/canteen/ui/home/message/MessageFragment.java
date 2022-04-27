package com.pky.canteen.ui.home.message;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pky.canteen.R;
import com.pky.canteen.base.fragment.BaseFragment;
import com.pky.canteen.ui.opiniondetails.OpinionDetailsActivity;

public class MessageFragment extends BaseFragment<MessageModel, MessageView> {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getV().getAdapter().setOnItemClickListener((data, position) -> {
            getModel().read(data.getId());
            data.setReading(true);
            getV().getAdapter().notifyDataSetChanged();
            OpinionDetailsActivity.start(requireContext(), data.getOpinionId());
        });
        getV().getAdapter().setOnItemLongClickListener((data, position) -> {
            getModel().unread(data.getId());
            toast("已标为未读");
            data.setReading(false);
            getV().getAdapter().notifyDataSetChanged();
        });
    }
}

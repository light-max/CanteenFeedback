package com.pky.canteen.ui.complaint;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.pky.canteen.R;
import com.pky.canteen.base.call.Base;
import com.pky.canteen.base.mvp.BaseView;
import com.pky.canteen.data.result.ComplaintDetails;

public class ComplaintView extends BaseView<ComplaintActivity> {
    @Override
    public void onCreate(Base base, Bundle savedInstanceState) {
        super.onCreate(base, savedInstanceState);
    }

    public void setData(ComplaintDetails data) {
        if (data == null) {
            get(R.id.send_view).setVisibility(View.VISIBLE);
            get(R.id.view_content).setVisibility(View.GONE);
            click(R.id.send, () -> {
                TextView content = get(R.id.content);
                base.getModel().sendComplaint(base.feedbackId, content.getText().toString());
            });
        } else {
            get(R.id.send_view).setVisibility(View.GONE);
            get(R.id.view_content).setVisibility(View.VISIBLE);
            TextView des = get(R.id.des);
            TextView desTime = get(R.id.des_time);
            des.setText(data.getDes());
            desTime.setText(data.getCreateTime());
            if (data.getResult() != null) {
                get(R.id.view_result).setVisibility(View.VISIBLE);
                get(R.id.view_no_result).setVisibility(View.GONE);
                TextView result = get(R.id.result);
                TextView resultTime = get(R.id.result_time);
                result.setText(data.getResult());
                resultTime.setText(data.getResultTime());
            } else {
                get(R.id.view_result).setVisibility(View.GONE);
                get(R.id.view_no_result).setVisibility(View.VISIBLE);
            }
        }
    }
}

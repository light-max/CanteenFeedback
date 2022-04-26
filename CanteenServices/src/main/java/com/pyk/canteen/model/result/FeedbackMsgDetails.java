package com.pyk.canteen.model.result;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FeedbackMsgDetails {
    private Integer id;
    private String recipienUid;
    private Integer feedbackId;
    private Integer opinionId;
    private Long createTime;
    private Boolean reading;

    private String createTimeText;
    private String opinionContent;
    private String feedbackContent;
}

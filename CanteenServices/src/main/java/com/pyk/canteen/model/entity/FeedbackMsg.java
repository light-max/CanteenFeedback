package com.pyk.canteen.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_feedback_msg")
public class FeedbackMsg {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("recipientUid")
    private String recipientUid;

    @TableField("feedbackId")
    private Integer feedbackId;

    @TableField("opinionId")
    private Integer opinionId;

    @TableField(fill = FieldFill.INSERT)
    private Long createTime;

    private Boolean reading;
}

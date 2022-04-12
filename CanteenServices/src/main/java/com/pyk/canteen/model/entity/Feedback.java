package com.pyk.canteen.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.pyk.canteen.fieldcheck.annotation.StringLengthMax;
import com.pyk.canteen.fieldcheck.annotation.StringLengthMin;
import com.pyk.canteen.fieldcheck.annotation.StringNonNull;
import com.pyk.canteen.fieldcheck.interfaces.FieldCheckInterface;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_feedback")
public class Feedback implements FieldCheckInterface<Feedback> {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("dishId")
    private Integer dishId;

    @TableField("opinionId")
    private Integer opinionId;

    @StringNonNull("反馈内容不能为空")
    @StringLengthMax(msg = "反馈内容不能超过${value}个字符", value = 300)
    @StringLengthMin(msg = "反馈内部不能小于${value}个字符", value = 10)
    private String content;

    @TableField(fill = FieldFill.INSERT)
    private Long createTime;
}

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
@TableName("T_opinion")
public class Opinion implements FieldCheckInterface<Opinion> {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("dishId")
    private Integer dishId;

    @TableField("createById")
    private String createById;

    @StringLengthMax(msg = "意见内容应该在${value}字以内", value = 300)
    @StringLengthMin(msg = "意见内容不能小于${value}个字符", value = 10)
    @StringNonNull("意见内容不能为空")
    private String content;

    @TableField(fill = FieldFill.INSERT)
    private Long createTime;

    @TableField("feedbackId")
    private Integer feedbackId;
}

package com.pyk.canteen.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.pyk.canteen.fieldcheck.annotation.StringLengthMax;
import com.pyk.canteen.fieldcheck.annotation.StringLengthMin;
import com.pyk.canteen.fieldcheck.annotation.StringNonNull;
import com.pyk.canteen.fieldcheck.interfaces.FieldCheckInterface;
import com.pyk.canteen.util.datetranslate.DateParameter;
import com.pyk.canteen.util.datetranslate.DateTranslate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_complaint")
public class Complaint implements FieldCheckInterface<Complaint>, DateTranslate {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("stallId")
    private Integer stallId;

    @TableField("dishId")
    private Integer dishId;

    @TableField("opinionId")
    private Integer opinionId;

    @TableField("feedbackId")
    private Integer feedbackId;

    @TableField("createById")
    private String createById;

    @StringLengthMax(msg = "申诉信息不能超过${value}个字符", value = 100)
    @StringLengthMin(msg = "申诉信息不能少于${value}个字符", value = 10)
    @StringNonNull("申诉信息不能为空")
    private String des;

    @StringLengthMax(msg = "回复不能超过${value}个字符", value = 300)
    @StringNonNull("回复内容不能为空")
    private String result;

    @TableField(fill = FieldFill.INSERT)
    @DateParameter
    private Long createTime;

    private Long resultTime;
}

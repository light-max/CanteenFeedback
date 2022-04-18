package com.pyk.canteen.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.pyk.canteen.fieldcheck.annotation.StringLengthMax;
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
@TableName("t_remark")
public class Remark implements FieldCheckInterface<Remark> {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("dishId")
    private Integer dishId;

    @TableField("createById")
    private String createById;

    @TableField("parentId")
    private Integer parentId;

    @StringNonNull("评论内容不能为空")
    @StringLengthMax(msg = "评论不能超过${value}个字符", value = 300)
    private String content;

    @TableField(fill = FieldFill.INSERT)
    private Long createTime;
}

package com.pyk.canteen.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("t_stall")
public class Stall implements FieldCheckInterface<Stall> {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @StringLengthMax(msg = "档口名称不能超过${value}个字符", value = 40)
    @StringNonNull("档口名称不能为空")
    private String name;

    @StringLengthMax(msg = "档口简介不能超过${value}个字符",value = 300)
    private String des;

    private Boolean enable;
}

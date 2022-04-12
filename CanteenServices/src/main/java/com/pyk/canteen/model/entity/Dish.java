package com.pyk.canteen.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pyk.canteen.fieldcheck.annotation.NumberEnum;
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
@TableName("t_dish")
public class Dish implements FieldCheckInterface<Dish> {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer sid;

    @NumberEnum(msg = "菜品ID错误", value = {1, 2, 3, 4, 5, 6, 7, 8})
    private Integer cid;

    @StringLengthMax(msg = "菜品名称不能超过${value}个字符", value = 30)
    @StringNonNull("菜品名称不能为空")
    private String name;

    @StringLengthMax(msg = "菜品描述不能超过${value}个字符", value = 300)
    private String des;

    @StringLengthMax(msg = "菜品原材料不能超过${value}个字符", value = 300)
    @StringNonNull("菜品原材料不能为空")
    private String material;

    private Boolean enable;
}

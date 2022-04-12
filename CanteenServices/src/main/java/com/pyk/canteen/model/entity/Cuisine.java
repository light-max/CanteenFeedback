package com.pyk.canteen.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pyk.canteen.fieldcheck.annotation.NumberEnum;
import com.pyk.canteen.fieldcheck.annotation.StringLengthMax;
import com.pyk.canteen.fieldcheck.interfaces.FieldCheckInterface;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_cuisine")
public class Cuisine implements FieldCheckInterface<Cuisine> {
    @TableId
    @NumberEnum(value = {1, 2, 3, 4, 5, 6, 7, 8})
    private Integer id;

    @StringLengthMax(8)
    private String name;
}

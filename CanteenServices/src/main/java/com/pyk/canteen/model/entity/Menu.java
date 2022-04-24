package com.pyk.canteen.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pyk.canteen.fieldcheck.annotation.DateFormat;
import com.pyk.canteen.fieldcheck.interfaces.FieldCheckInterface;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_menu")
public class Menu implements FieldCheckInterface<Menu> {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @DateFormat("yyyy-MM-dd")
    private String date;
}

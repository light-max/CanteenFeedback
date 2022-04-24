package com.pyk.canteen.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_menu_item")
public class MenuItem {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("menuId")
    private Integer menuId;

    @TableField("dishId")
    private Integer dishId;
}

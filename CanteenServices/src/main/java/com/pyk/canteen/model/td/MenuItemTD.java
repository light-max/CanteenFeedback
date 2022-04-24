package com.pyk.canteen.model.td;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MenuItemTD {
    private Integer id;
    private Integer menuId;
    private Integer dishId;
    private Integer stallId;

    private String dishName;
    private String dishDes;
    private String dishMaterial;
    private String dishCover;
    private String stallName;
}

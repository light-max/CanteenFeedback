package com.pyk.canteen.model.td;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DishTD {
    private Integer id;
    private String name;
    private String des;
    private String material;
    private String stallName;
    private String cover;
}

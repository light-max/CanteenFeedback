package com.pyk.canteen.model.result;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class DishDetails {
    private int id;
    private int stallId;
    private int cid;
    private String name;
    private String des;
    private String material;

    private String stallName;
    private String stallDes;
    private String cuisine;

    private String cover;
    private List<String> images;
}

package com.pyk.canteen.model.result;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Collector {
    private String uid;
    private String name;
    private String head;
    private int dishId;
}

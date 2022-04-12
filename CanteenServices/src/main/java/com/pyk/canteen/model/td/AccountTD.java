package com.pyk.canteen.model.td;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccountTD {
    private String uid;
    private Integer type;
    private String typeName;
    private String name;
}

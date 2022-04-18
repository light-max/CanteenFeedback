package com.pyk.canteen.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AccountAddRequest {
    private Integer index;
    private String uid;
    private String pwd;
    private String name;
}

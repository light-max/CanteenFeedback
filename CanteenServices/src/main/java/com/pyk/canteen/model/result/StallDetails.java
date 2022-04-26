package com.pyk.canteen.model.result;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class StallDetails {
    private Integer id;
    private String name;
    private String des;
    private String cover;
    private List<String> images;
}

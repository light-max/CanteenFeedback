package com.pyk.canteen.model.result;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class COpinion {
    private Integer id;
    private Integer dishId;
    private String createById;
    private String content;
    private Long createTime;
    private Integer feedbackId;

    private Integer stallId;

    private String createTimeText;
    private String stallName;
    private String dishName;
    private String dishCover;

    private List<String> images;
    private String video;
}

package com.pyk.canteen.model.td;

import com.pyk.canteen.model.result.Images;
import com.pyk.canteen.util.datetranslate.DateParameter;
import com.pyk.canteen.util.datetranslate.DateTranslate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OpinionTD implements DateTranslate {
    private Integer id;
    private Integer dishId;
    private String createById;
    private String content;
    @DateParameter
    private Long createTime;
    private Integer feedbackId;

    private Integer stallId;
    private String dishName;
    private String stallName;
    private String createByName;
    private String createByHead;
    private String createByType;
    private Images images;
    private String video;
}

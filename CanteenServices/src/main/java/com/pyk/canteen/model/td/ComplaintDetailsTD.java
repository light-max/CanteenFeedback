package com.pyk.canteen.model.td;

import com.pyk.canteen.model.result.Images;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ComplaintDetailsTD{
    private Integer id;
    private String des;
    private String result;
    private String createTime;
    private String resultTime;

    private Integer stallId;
    private String stallName;
    private String stallDes;

    private Integer dishId;
    private String dishName;
    private String dishDes;
    private String dishMaterial;
    private Images dishImages;

    private Integer opinionId;
    private String opinionContent;
    private String opinionVideo;
    private Images opinionImages;
    private String opinionCreateTime;

    private Integer feedbackId;
    private String feedbackContent;
    private String feedbackCreateTime;

    private String createById;
    private String createByName;
    private String createByHead;
    private String createByType;
}

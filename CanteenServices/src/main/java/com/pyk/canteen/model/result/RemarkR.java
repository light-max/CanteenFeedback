package com.pyk.canteen.model.result;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RemarkR {
    private Integer id;
    private Integer dishId;
    private String createById;
    private Integer parentId;
    private String content;
    private Long createTime;

    private String createByHead;
    private String createByName;
    private String createTimeText;
    private int replyCount;
}

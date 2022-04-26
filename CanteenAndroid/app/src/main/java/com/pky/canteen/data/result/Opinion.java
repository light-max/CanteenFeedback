package com.pky.canteen.data.result;

public class Opinion {

    private Integer id;
    private Integer dishId;
    private String createById;
    private String content;
    private Long createTime;
    private Object feedbackId;
    private Integer stallId;
    private String createTimeText;
    private String stallName;
    private String dishName;
    private String dishCover;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public String getCreateById() {
        return createById;
    }

    public void setCreateById(String createById) {
        this.createById = createById;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Object getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Object feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Integer getStallId() {
        return stallId;
    }

    public void setStallId(Integer stallId) {
        this.stallId = stallId;
    }

    public String getCreateTimeText() {
        return createTimeText;
    }

    public void setCreateTimeText(String createTimeText) {
        this.createTimeText = createTimeText;
    }

    public String getStallName() {
        return stallName;
    }

    public void setStallName(String stallName) {
        this.stallName = stallName;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getDishCover() {
        return dishCover;
    }

    public void setDishCover(String dishCover) {
        this.dishCover = dishCover;
    }
}

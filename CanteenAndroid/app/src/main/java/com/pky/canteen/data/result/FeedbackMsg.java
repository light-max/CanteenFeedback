package com.pky.canteen.data.result;

public class FeedbackMsg {
    private Integer id;
    private String recipienUid;
    private Integer feedbackId;
    private Integer opinionId;
    private Long createTime;
    private Boolean reading;
    private String createTimeText;
    private String opinionContent;
    private String feedbackContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRecipienUid() {
        return recipienUid;
    }

    public void setRecipienUid(String recipienUid) {
        this.recipienUid = recipienUid;
    }

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Integer getOpinionId() {
        return opinionId;
    }

    public void setOpinionId(Integer opinionId) {
        this.opinionId = opinionId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Boolean getReading() {
        return reading;
    }

    public void setReading(Boolean reading) {
        this.reading = reading;
    }

    public String getCreateTimeText() {
        return createTimeText;
    }

    public void setCreateTimeText(String createTimeText) {
        this.createTimeText = createTimeText;
    }

    public String getOpinionContent() {
        return opinionContent;
    }

    public void setOpinionContent(String opinionContent) {
        this.opinionContent = opinionContent;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }
}

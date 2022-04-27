package com.pky.canteen.data.result;

import java.util.List;

public class ComplaintDetails {
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
    private DishImagesDTO dishImages;
    private Integer opinionId;
    private String opinionContent;
    private Object opinionVideo;
    private OpinionImagesDTO opinionImages;
    private String opinionCreateTime;
    private Integer feedbackId;
    private String feedbackContent;
    private String feedbackCreateTime;
    private String createById;
    private String createByName;
    private String createByHead;
    private String createByType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getResultTime() {
        return resultTime;
    }

    public void setResultTime(String resultTime) {
        this.resultTime = resultTime;
    }

    public Integer getStallId() {
        return stallId;
    }

    public void setStallId(Integer stallId) {
        this.stallId = stallId;
    }

    public String getStallName() {
        return stallName;
    }

    public void setStallName(String stallName) {
        this.stallName = stallName;
    }

    public String getStallDes() {
        return stallDes;
    }

    public void setStallDes(String stallDes) {
        this.stallDes = stallDes;
    }

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getDishDes() {
        return dishDes;
    }

    public void setDishDes(String dishDes) {
        this.dishDes = dishDes;
    }

    public String getDishMaterial() {
        return dishMaterial;
    }

    public void setDishMaterial(String dishMaterial) {
        this.dishMaterial = dishMaterial;
    }

    public DishImagesDTO getDishImages() {
        return dishImages;
    }

    public void setDishImages(DishImagesDTO dishImages) {
        this.dishImages = dishImages;
    }

    public Integer getOpinionId() {
        return opinionId;
    }

    public void setOpinionId(Integer opinionId) {
        this.opinionId = opinionId;
    }

    public String getOpinionContent() {
        return opinionContent;
    }

    public void setOpinionContent(String opinionContent) {
        this.opinionContent = opinionContent;
    }

    public Object getOpinionVideo() {
        return opinionVideo;
    }

    public void setOpinionVideo(Object opinionVideo) {
        this.opinionVideo = opinionVideo;
    }

    public OpinionImagesDTO getOpinionImages() {
        return opinionImages;
    }

    public void setOpinionImages(OpinionImagesDTO opinionImages) {
        this.opinionImages = opinionImages;
    }

    public String getOpinionCreateTime() {
        return opinionCreateTime;
    }

    public void setOpinionCreateTime(String opinionCreateTime) {
        this.opinionCreateTime = opinionCreateTime;
    }

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public String getFeedbackCreateTime() {
        return feedbackCreateTime;
    }

    public void setFeedbackCreateTime(String feedbackCreateTime) {
        this.feedbackCreateTime = feedbackCreateTime;
    }

    public String getCreateById() {
        return createById;
    }

    public void setCreateById(String createById) {
        this.createById = createById;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public String getCreateByHead() {
        return createByHead;
    }

    public void setCreateByHead(String createByHead) {
        this.createByHead = createByHead;
    }

    public String getCreateByType() {
        return createByType;
    }

    public void setCreateByType(String createByType) {
        this.createByType = createByType;
    }

    public static class DishImagesDTO {
        private Integer id;
        private List<String> urls;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public List<String> getUrls() {
            return urls;
        }

        public void setUrls(List<String> urls) {
            this.urls = urls;
        }
    }

    public static class OpinionImagesDTO {
        private Integer id;
        private List<String> urls;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public List<String> getUrls() {
            return urls;
        }

        public void setUrls(List<String> urls) {
            this.urls = urls;
        }
    }
}

package com.pyk.canteen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pyk.canteen.model.entity.Feedback;

public interface FeedbackService extends IService<Feedback> {
    void post(Integer opinionId, String content);
}

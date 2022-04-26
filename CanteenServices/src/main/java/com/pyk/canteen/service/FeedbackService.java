package com.pyk.canteen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pyk.canteen.model.entity.Feedback;
import com.pyk.canteen.model.result.FeedbackMsgDetails;

import java.util.List;

public interface FeedbackService extends IService<Feedback> {
    void post(Integer opinionId, String content);

    List<FeedbackMsgDetails> getMsgListByUid(String uid);

    void readMsg(Integer id, String uid);

    void unreadMsg(Integer id, String uid);
}

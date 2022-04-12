package com.pyk.canteen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pyk.canteen.constant.GlobalConstant;
import com.pyk.canteen.mapper.FeedbackMapper;
import com.pyk.canteen.mapper.FeedbackMsgMapper;
import com.pyk.canteen.mapper.OpinionMapper;
import com.pyk.canteen.model.entity.Feedback;
import com.pyk.canteen.model.entity.FeedbackMsg;
import com.pyk.canteen.model.entity.Opinion;
import com.pyk.canteen.service.FeedbackService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements FeedbackService {
    @Resource
    OpinionMapper opinionMapper;

    @Resource
    FeedbackMsgMapper msgMapper;

    @Override
    public void post(Integer opinionId, String content) {
        Opinion opinion = opinionMapper.selectById(opinionId);
        GlobalConstant.dataNotExists.notNull(opinion);
        Feedback feedback = Feedback.builder()
                .opinionId(opinionId)
                .dishId(opinion.getDishId())
                .content(content)
                .build()
                .check();
        save(feedback);
        opinion.setFeedbackId(feedback.getId());
        opinionMapper.updateById(opinion);
        msgMapper.insert(FeedbackMsg.builder()
                .opinionId(opinionId)
                .feedbackId(feedback.getId())
                .recipientUid(opinion.getCreateById())
                .build());
    }
}

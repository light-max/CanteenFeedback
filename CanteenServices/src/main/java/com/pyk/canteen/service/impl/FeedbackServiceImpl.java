package com.pyk.canteen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pyk.canteen.constant.GlobalConstant;
import com.pyk.canteen.mapper.FeedbackMapper;
import com.pyk.canteen.mapper.FeedbackMsgMapper;
import com.pyk.canteen.mapper.OpinionMapper;
import com.pyk.canteen.model.entity.Feedback;
import com.pyk.canteen.model.entity.FeedbackMsg;
import com.pyk.canteen.model.entity.Opinion;
import com.pyk.canteen.model.result.FeedbackMsgDetails;
import com.pyk.canteen.service.FeedbackService;
import com.pyk.canteen.util.datetranslate.DefaultDataTranslate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<FeedbackMsgDetails> getMsgListByUid(String uid) {
        LambdaQueryWrapper<FeedbackMsg> wrapper = new QueryWrapper<FeedbackMsg>()
                .lambda()
                .eq(FeedbackMsg::getRecipientUid, uid)
                .orderByDesc(FeedbackMsg::getCreateTime);
        List<FeedbackMsg> msgs = msgMapper.selectList(wrapper);
        return new ArrayList<FeedbackMsgDetails>() {{
            for (FeedbackMsg msg : msgs) {
                Opinion opinion = opinionMapper.selectById(msg.getOpinionId());
                Feedback feedback = getById(msg.getFeedbackId());
                add(FeedbackMsgDetails.builder()
                        .id(msg.getId())
                        .createTime(msg.getCreateTime())
                        .feedbackId(msg.getFeedbackId())
                        .opinionId(msg.getOpinionId())
                        .reading(msg.getReading())
                        .recipienUid(msg.getRecipientUid())
                        .createTimeText(new DefaultDataTranslate(msg.getCreateTime()).translateDate())
                        .opinionContent(opinion.getContent())
                        .feedbackContent(feedback.getContent())
                        .build());
            }
        }};
    }

    @Override
    public void readMsg(Integer id, String uid) {
        FeedbackMsg msg = msgMapper.selectById(id);
        GlobalConstant.dataNotExists.isTrue(msg.getRecipientUid().equals(uid));
        msg.setReading(true);
        msgMapper.updateById(msg);
    }

    @Override
    public void unreadMsg(Integer id, String uid) {
        FeedbackMsg msg = msgMapper.selectById(id);
        GlobalConstant.dataNotExists.isTrue(msg.getRecipientUid().equals(uid));
        msg.setReading(false);
        msgMapper.updateById(msg);
    }
}

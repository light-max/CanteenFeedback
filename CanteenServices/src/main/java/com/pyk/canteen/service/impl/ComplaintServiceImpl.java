package com.pyk.canteen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pyk.canteen.constant.GlobalConstant;
import com.pyk.canteen.controller.ResourceController;
import com.pyk.canteen.mapper.*;
import com.pyk.canteen.model.entity.*;
import com.pyk.canteen.model.result.Images;
import com.pyk.canteen.model.td.ComplaintDetailsTD;
import com.pyk.canteen.service.ComplaintService;
import com.pyk.canteen.util.FileTools;
import com.pyk.canteen.util.datetranslate.DefaultDataTranslate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;

@Service
public class ComplaintServiceImpl extends ServiceImpl<ComplaintMapper, Complaint> implements ComplaintService {
    @Value("${file.video.opinion}")
    private String opinionVideosPath;

    @Resource
    FeedbackMapper feedbackMapper;

    @Resource
    OpinionMapper opinionMapper;

    @Resource
    DishMapper dishMapper;

    @Resource
    StallMapper stallMapper;

    @Resource
    AccountMapper accountMapper;

    @Resource
    TeacherMapper teacherMapper;

    @Resource
    StudentMapper studentMapper;

    @Resource
    ResourceController resourceController;

    @Override
    public void post(String uid, Integer feedbackId, String des) {
        Feedback feedback = feedbackMapper.selectById(feedbackId);
        GlobalConstant.dataNotExists.notNull(feedback);
        Opinion opinion = opinionMapper.selectById(feedback.getOpinionId());
        GlobalConstant.dataNotExists.notNull(opinion);
        GlobalConstant.noAccess.isTrue(opinion.getCreateById().equals(uid));
        Dish dish = dishMapper.selectById(opinion.getDishId());
        Complaint complaint = Complaint.builder()
                .createById(uid)
                .des(des)
                .dishId(feedback.getDishId())
                .feedbackId(feedbackId)
                .stallId(dish.getSid())
                .opinionId(opinion.getId())
                .build()
                .check();
        save(complaint);
    }

    @Override
    public Page<Complaint> list(Integer n, int status) {
        LambdaQueryWrapper<Complaint> wrapper = new QueryWrapper<Complaint>()
                .lambda()
                .orderByDesc(Complaint::getCreateTime);
        if (status == 0) {
            wrapper.isNull(Complaint::getResultTime);
        } else if (status == 1) {
            wrapper.isNotNull(Complaint::getResultTime);
        }
        return page(new Page<>(n == null ? 0 : n, 10), wrapper);
    }

    @Override
    public ComplaintDetailsTD details(Integer id) {
        Complaint complaint = getById(id);
        Stall stall = stallMapper.selectById(complaint.getStallId());
        Dish dish = dishMapper.selectById(complaint.getDishId());
        Opinion opinion = opinionMapper.selectById(complaint.getOpinionId());
        Feedback feedback = feedbackMapper.selectById(complaint.getFeedbackId());
        GlobalConstant.dataNotExists.notNull(complaint);
        GlobalConstant.dataNotExists.notNull(stall);
        GlobalConstant.dataNotExists.notNull(dish);
        GlobalConstant.dataNotExists.notNull(opinion);
        GlobalConstant.dataNotExists.notNull(feedback);
        String createByName = complaint.getCreateById();
        Account account = accountMapper.selectById(complaint.getCreateById());
        if (account.getType() == 2) {
            createByName = teacherMapper.selectById(complaint.getCreateById()).getName();
        } else if (account.getType() == 3) {
            createByName = studentMapper.selectById(complaint.getCreateById()).getName();
        }
        Images opinionImages = resourceController.getImages("opinion", opinion.getId()).getData();
        File opinionVideoFile = FileTools.getVideoFilePath(opinionVideosPath, opinion.getId());
        return ComplaintDetailsTD.builder()
                .id(complaint.getId())
                .des(complaint.getDes())
                .result(complaint.getResult())
                .createTime(new DefaultDataTranslate(complaint.getCreateTime()).translateDate())
                .result(complaint.getResult())
                .resultTime(complaint.getResultTime() == null ? "" : new DefaultDataTranslate(complaint.getResultTime()).translateDate())
                .stallId(stall.getId())
                .stallName(stall.getName())
                .stallDes(stall.getDes())
                .dishId(dish.getId())
                .dishName(dish.getName())
                .dishDes(dish.getDes())
                .dishImages(resourceController.getImages("dish", dish.getId()).getData())
                .dishMaterial(dish.getMaterial())
                .opinionId(opinion.getId())
                .opinionContent(opinion.getContent())
                .opinionImages(opinionImages)
                .opinionVideo(opinionVideoFile.exists() ? "/opinion/video/" + opinion.getId() : null)
                .opinionCreateTime(new DefaultDataTranslate(opinion.getCreateTime()).translateDate())
                .feedbackId(feedback.getId())
                .feedbackContent(feedback.getContent())
                .feedbackCreateTime(new DefaultDataTranslate(feedback.getCreateTime()).translateDate())
                .createById(account.getUid())
                .createByName(createByName)
                .createByHead("/head/" + account.getUid())
                .createByType(account.getType() == 3 ? "学生" : "教师")
                .build();
    }

    @Override
    public void execute(Integer id, String result) {
        Complaint complaint = getById(id);
        GlobalConstant.dataNotExists.notNull(complaint);
        complaint.setResult(result);
        complaint.setResultTime(System.currentTimeMillis());
        complaint.check();
        updateById(complaint);
    }
}

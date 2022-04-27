package com.pyk.canteen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pyk.canteen.constant.GlobalConstant;
import com.pyk.canteen.controller.ResourceController;
import com.pyk.canteen.mapper.*;
import com.pyk.canteen.model.data.Result;
import com.pyk.canteen.model.entity.Account;
import com.pyk.canteen.model.entity.Dish;
import com.pyk.canteen.model.entity.Opinion;
import com.pyk.canteen.model.entity.Stall;
import com.pyk.canteen.model.result.COpinion;
import com.pyk.canteen.model.result.Images;
import com.pyk.canteen.model.td.OpinionTD;
import com.pyk.canteen.service.OpinionService;
import com.pyk.canteen.util.FileTools;
import com.pyk.canteen.util.datetranslate.DefaultDataTranslate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OpinionServiceImpl extends ServiceImpl<OpinionMapper, Opinion> implements OpinionService {
    @Value("${file.video.opinion}")
    private String opinionVideosPath;

    @Value("${file.images.opinion}")
    private String opinionImagesPath;

    @Resource
    DishMapper dishMapper;

    @Resource
    StallMapper stallMapper;

    @Resource
    AccountMapper accountMapper;

    @Resource
    StudentMapper studentMapper;

    @Resource
    TeacherMapper teacherMapper;

    @Resource
    ResourceController resourceController;

    @Override
    public Page<Opinion> queryByStatus(Integer n, int status) {
        LambdaQueryWrapper<Opinion> wrapper = new QueryWrapper<Opinion>()
                .lambda()
                .orderByDesc(Opinion::getCreateTime);
        if (status == 0) {
            wrapper.isNull(Opinion::getFeedbackId);
        } else if (status == 1) {
            wrapper.isNotNull(Opinion::getFeedbackId);
        }
        return page(new Page<>(n == null ? 0 : n, 4), wrapper);
    }

    @Override
    public void post(
            Account account, Integer dishId, String content,
            List<MultipartFile> images, MultipartFile video) {
        GlobalConstant.dataNotExists.notNull(dishMapper.selectById(dishId));
        Opinion opinion = Opinion.builder()
                .createById(account.getUid())
                .dishId(dishId)
                .content(content)
                .build()
                .check();
        save(opinion);
        try {
            if (!images.isEmpty()) {
//                FileTools.getImagePath(opinionImagesPath, opinion.getId()).mkdirs();
//                for (int i = 0; i < images.size(); i++) {
//                    File path = FileTools.getImagePath(opinionImagesPath, opinion.getId(), i);
//                    FileTools.write(images.get(i).getInputStream(), path);
//                }
                for (MultipartFile image : images) {
                    if (image.isEmpty()) {
                        continue;
                    }
                    resourceController.addImage("opinion", opinion.getId(), image);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (video != null && !video.isEmpty()) {
            try {
                File path = FileTools.getVideoFilePath(opinionVideosPath, opinion.getId());
                FileTools.write(video.getInputStream(), path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<COpinion> list(Account account, Integer dishId) {
        LambdaQueryWrapper<Opinion> wrapper = new QueryWrapper<Opinion>().lambda()
                .eq(Opinion::getCreateById, account.getUid());
        if (dishId != null) {
            wrapper.eq(Opinion::getDishId, dishId);
        }
        List<Opinion> list = list(wrapper);
        List<COpinion> result = new ArrayList<>();
        for (Opinion o : list) {
            Dish dish = dishMapper.selectById(o.getDishId());
            Result<Images> images = resourceController.getImages("dish", dish.getId());
            Stall stall = stallMapper.selectById(dish.getSid());
            List<String> urls = images.getData().getUrls();
            File videoFile = FileTools.getVideoFilePath(opinionVideosPath, o.getId());
            result.add(COpinion.builder()
                    .id(o.getId())
                    .content(o.getContent())
                    .createById(o.getCreateById())
                    .createTime(o.getCreateTime())
                    .createTimeText(new DefaultDataTranslate(o.getCreateTime()).translateDate())
                    .feedbackId(o.getFeedbackId())
                    .dishCover(urls.isEmpty() ? "/images/no-image.jpg" : urls.get(0))
                    .dishId(dish.getId())
                    .dishName(dish.getName())
                    .stallId(stall.getId())
                    .images(resourceController.getImages("opinion", o.getId()).getData().getUrls())
                    .stallName(stall.getName())
                    .video(videoFile.exists() ? "/opinion/video/" + o.getId() : null)
                    .build());
        }
        return result;
    }

    @Override
    public List<OpinionTD> get(List<Opinion> list) {
        return new ArrayList<OpinionTD>() {{
            for (Opinion o : list) {
                Dish dish = dishMapper.selectById(o.getDishId());
                Stall stall = stallMapper.selectById(dish.getSid());
                String createByName = o.getCreateById();
                Account account = accountMapper.selectById(o.getCreateById());
                if (account.getType() == 2) {
                    createByName = teacherMapper.selectById(o.getCreateById()).getName();
                } else if (account.getType() == 3) {
                    createByName = studentMapper.selectById(o.getCreateById()).getName();
                }
                Images images = resourceController.getImages("opinion", o.getId()).getData();
                File videoFile = FileTools.getVideoFilePath(opinionVideosPath, o.getId());
                add(OpinionTD.builder()
                        .id(o.getId())
                        .dishId(o.getDishId())
                        .createById(o.getCreateById())
                        .createTime(o.getCreateTime())
                        .feedbackId(o.getFeedbackId())
                        .content(o.getContent())
                        .stallId(stall.getId())
                        .stallName(stall.getName())
                        .dishName(dish.getName())
                        .createByName(createByName)
                        .createByHead("/head/" + o.getCreateById())
                        .createByType(account.getType() == 2 ? "教师" : "学生")
                        .images(images)
                        .video(videoFile.exists() ? "/opinion/video/" + o.getId() : null)
                        .videoPlay(videoFile.exists() ? "/opinion/video/play/" + o.getId() : null)
                        .build());
            }
        }};
    }

    @Override
    public COpinion getDetails(Integer id) {
        Opinion o = getById(id);
        Dish dish = dishMapper.selectById(o.getDishId());
        Stall stall = stallMapper.selectById(dish.getSid());
        Result<Images> images = resourceController.getImages("dish", dish.getId());
        List<String> urls = images.getData().getUrls();
        File videoFile = FileTools.getVideoFilePath(opinionVideosPath, o.getId());
        return COpinion.builder()
                .id(o.getId())
                .content(o.getContent())
                .createById(o.getCreateById())
                .createTime(o.getCreateTime())
                .createTimeText(new DefaultDataTranslate(o.getCreateTime()).translateDate())
                .feedbackId(o.getFeedbackId())
                .dishCover(urls.isEmpty() ? "/images/no-image.jpg" : urls.get(0))
                .dishId(dish.getId())
                .dishName(dish.getName())
                .stallId(stall.getId())
                .stallName(stall.getName())
                .images(resourceController.getImages("opinion", o.getId()).getData().getUrls())
                .video(videoFile.exists() ? "/opinion/video/" + o.getId() : null)
                .build();
    }
}

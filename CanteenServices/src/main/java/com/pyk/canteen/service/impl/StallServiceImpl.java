package com.pyk.canteen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pyk.canteen.controller.ResourceController;
import com.pyk.canteen.mapper.StallMapper;
import com.pyk.canteen.model.data.Result;
import com.pyk.canteen.model.entity.Stall;
import com.pyk.canteen.model.result.Images;
import com.pyk.canteen.model.result.StallDetails;
import com.pyk.canteen.service.StallService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class StallServiceImpl extends ServiceImpl<StallMapper, Stall> implements StallService {
    @Resource
    ResourceController resourceController;

    @Override
    public List<Stall> list(Boolean enable) {
        if (enable == null) {
            return list();
        } else {
            return list(new QueryWrapper<Stall>()
                    .lambda()
                    .eq(Stall::getEnable, enable)
            );
        }
    }

    @Override
    public Stall add(String name, String des) {
        Stall stall = Stall.builder()
                .name(name)
                .des(des)
                .build();
        save(stall.check());
        return stall;
    }

    @Override
    public Stall update(Integer id, String name, String des, Boolean enable) {
        Stall stall = Stall.builder()
                .id(id)
                .name(name)
                .des(des)
                .enable(enable)
                .build();
        updateById(stall.check());
        return stall;
    }

    @Override
    public List<StallDetails> getAllStall() {
        List<Stall> list = list();
        return new ArrayList<StallDetails>() {{
            for (Stall stall : list) {
                add(getStallDetails(stall.getId()));
            }
        }};
    }

    @Override
    public StallDetails getStallDetails(int id) {
        Stall stall = getById(id);
        Result<Images> images = resourceController.getImages("stall", id);
        List<String> urls = images.getData().getUrls();
        return StallDetails.builder()
                .id(stall.getId())
                .name(stall.getName())
                .des(stall.getDes())
                .cover(urls.isEmpty() ? "/images/no-image.jpg" : urls.get(0))
                .images(urls)
                .build();
    }

    @Override
    public List<Stall> search(String value) {
        return list(new QueryWrapper<Stall>()
                .lambda()
                .like(Stall::getName, value));
    }
}

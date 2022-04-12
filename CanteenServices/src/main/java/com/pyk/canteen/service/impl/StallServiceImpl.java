package com.pyk.canteen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pyk.canteen.mapper.StallMapper;
import com.pyk.canteen.model.entity.Stall;
import com.pyk.canteen.service.StallService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StallServiceImpl extends ServiceImpl<StallMapper, Stall> implements StallService {
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
}

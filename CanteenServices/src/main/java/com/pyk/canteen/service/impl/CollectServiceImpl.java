package com.pyk.canteen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pyk.canteen.constant.GlobalConstant;
import com.pyk.canteen.mapper.*;
import com.pyk.canteen.model.entity.Account;
import com.pyk.canteen.model.entity.Collect;
import com.pyk.canteen.model.entity.Dish;
import com.pyk.canteen.model.result.Collector;
import com.pyk.canteen.model.result.DishDetails;
import com.pyk.canteen.service.CollectService;
import com.pyk.canteen.service.DishService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements CollectService {
    @Resource
    DishMapper dishMapper;

    @Resource
    AccountMapper accountMapper;

    @Resource
    StudentMapper studentMapper;

    @Resource
    TeacherMapper teacherMapper;

    @Resource
    DishService dishService;

    @Override
    public Collect collect(String uid, Integer dishId) {
        GlobalConstant.dataNotExists.notNull(dishMapper.selectById(dishId));
        Collect collect = getOne(new QueryWrapper<Collect>()
                .lambda()
                .eq(Collect::getUid, uid)
                .eq(Collect::getDishId, dishId));
        if (collect == null) {
            collect = Collect.builder()
                    .uid(uid)
                    .dishId(dishId)
                    .build();
            save(collect);
        } else {
            removeById(collect.getId());
            collect = null;
        }
        return collect;
    }

    @Override
    public Collect check(String uid, Integer dishId) {
        return getOne(new QueryWrapper<Collect>()
                .lambda()
                .eq(Collect::getUid, uid)
                .eq(Collect::getDishId, dishId)
        );
    }

    @Override
    public Integer count(Integer dishId) {
        return count(new QueryWrapper<Collect>()
                .lambda()
                .eq(Collect::getDishId, dishId));
    }

    @Override
    public Page<Collect> list(Integer n, Integer dishId) {
        return page(new Page<>(n == null ? 0 : n, 10),
                new QueryWrapper<Collect>()
                        .lambda()
                        .eq(Collect::getDishId, dishId)
        );
    }

    @Override
    public List<Collector> getCollector(List<Collect> list) {
        return new ArrayList<Collector>() {{
            for (Collect collect : list) {
                String name = collect.getUid();
                Account account = accountMapper.selectById(name);
                if (account.getType() == 2) {
                    name = teacherMapper.selectById(collect.getUid()).getName();
                } else if (account.getType() == 3) {
                    name = studentMapper.selectById(collect.getUid()).getName();
                }
                add(Collector.builder()
                        .uid(collect.getUid())
                        .dishId(collect.getDishId())
                        .head("/head/" + collect.getUid())
                        .name(name)
                        .build());
            }
        }};
    }

    @Override
    public List<DishDetails> getCollectAll(String uid) {
        List<Collect> list = list(new QueryWrapper<Collect>()
                .lambda()
                .eq(Collect::getUid, uid));
        if (list.isEmpty()) {
            return new ArrayList<>();
        }
        List<Integer> ids = new ArrayList<>();
        for (Collect collect : list) {
            ids.add(collect.getDishId());
        }
        List<Dish> dishes = dishMapper.selectBatchIds(ids);
        return dishService.getDishDetailsList(dishes);
    }
}

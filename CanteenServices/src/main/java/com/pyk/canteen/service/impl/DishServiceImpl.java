package com.pyk.canteen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pyk.canteen.constant.GlobalConstant;
import com.pyk.canteen.controller.ResourceController;
import com.pyk.canteen.mapper.DishMapper;
import com.pyk.canteen.mapper.StallMapper;
import com.pyk.canteen.model.entity.Dish;
import com.pyk.canteen.model.entity.Stall;
import com.pyk.canteen.model.result.Images;
import com.pyk.canteen.model.td.DishTD;
import com.pyk.canteen.service.DishService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
    @Resource
    StallMapper stallMapper;

    @Resource
    ResourceController resourceController;

    @Override
    public Page<Dish> query(Integer stallId, Integer n) {
        if (stallId == null) {
            return page(new Page<>(n == null ? 0 : n, 4));
        } else {
            return page(new Page<>(n == null ? 0 : n, 4),
                    new QueryWrapper<Dish>()
                            .lambda()
                            .eq(Dish::getSid, stallId)
            );
        }
    }

    @Override
    public Dish add(Integer stallId, String name, Integer cuisine, String des, String material) {
        Stall stall = stallMapper.selectById(stallId);
        GlobalConstant.dataNotExists.notNull(stall);
        Dish dish = Dish.builder()
                .sid(stallId)
                .cid(cuisine)
                .name(name)
                .des(des)
                .material(material)
                .build();
        save(dish.check());
        return dish;
    }

    @Override
    public Dish update(Integer id, String name, Integer cid, String des, String material, Boolean enable) {
        Dish dish = Dish.builder()
                .id(id)
                .name(name)
                .cid(cid)
                .des(des)
                .material(material)
                .enable(enable)
                .build();
        updateById(dish.check());
        return dish;
    }

    @Override
    public List<DishTD> getDishTD(List<Dish> dishes) {
        return new ArrayList<DishTD>() {{
            for (Dish dish : dishes) {
                Stall stall = stallMapper.selectById(dish.getSid());
                Images images = resourceController.getImages("dish", dish.getId()).getData();
                List<String> urls = images.getUrls();
                add(DishTD.builder()
                        .id(dish.getId())
                        .name(dish.getName())
                        .des(dish.getDes())
                        .material(dish.getMaterial())
                        .stallName(stall == null ? "" : stall.getName())
                        .cover(urls.isEmpty() ? "/images/no-image.jpg" : urls.get(0))
                        .build());
            }
        }};
    }
}

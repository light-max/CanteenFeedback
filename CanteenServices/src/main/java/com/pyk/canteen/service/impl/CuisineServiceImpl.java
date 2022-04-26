package com.pyk.canteen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pyk.canteen.mapper.CuisineMapper;
import com.pyk.canteen.model.entity.Cuisine;
import com.pyk.canteen.model.entity.Dish;
import com.pyk.canteen.service.CuisineService;
import com.pyk.canteen.service.DishService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CuisineServiceImpl implements CuisineService {
    @Resource
    CuisineMapper mapper;

    @Resource
    DishService dishService;

    @Override
    public List<Cuisine> list() {
        return mapper.selectList(Wrappers.emptyWrapper());
    }

    @Override
    public Page<Dish> getDishListByCuisine(Integer n, int cuisineId) {
        int size = 4;
        if (n == null || n <= 1) {
            size = 8;
        }
        LambdaQueryWrapper<Dish> wrapper = new QueryWrapper<Dish>()
                .lambda()
                .eq(Dish::getCid, cuisineId);
        return dishService.page(new Page<>(n == null ? 1 : n, size), wrapper);
    }
}

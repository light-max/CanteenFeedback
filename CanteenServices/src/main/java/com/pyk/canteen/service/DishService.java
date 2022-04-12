package com.pyk.canteen.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pyk.canteen.model.entity.Dish;
import com.pyk.canteen.model.td.DishTD;

import java.util.List;

public interface DishService extends IService<Dish> {
    Page<Dish> query(Integer stallId, Integer n);

    Dish add(Integer stallId, String name, Integer cuisine, String des, String material);

    Dish update(Integer id, String name, Integer cid, String des, String material, Boolean enable);

    List<DishTD> getDishTD(List<Dish> dishes);
}

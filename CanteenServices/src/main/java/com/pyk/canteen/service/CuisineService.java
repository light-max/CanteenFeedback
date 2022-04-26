package com.pyk.canteen.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pyk.canteen.model.entity.Cuisine;
import com.pyk.canteen.model.entity.Dish;

import java.util.List;

public interface CuisineService {
    List<Cuisine> list();

    Page<Dish> getDishListByCuisine(Integer n, int cuisineId);
}

package com.pyk.canteen.controller.mobile;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pyk.canteen.model.data.Pager;
import com.pyk.canteen.model.data.PagerData;
import com.pyk.canteen.model.data.Result;
import com.pyk.canteen.model.entity.Cuisine;
import com.pyk.canteen.model.entity.Dish;
import com.pyk.canteen.model.result.DishDetails;
import com.pyk.canteen.service.CuisineService;
import com.pyk.canteen.service.DishService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller("mobile.cuisine")
public class CuisineController {
    @Resource
    CuisineService service;

    @Resource
    DishService dishService;

    @GetMapping("/cuisine/list")
    @ResponseBody
    public Result<List<Cuisine>> list() {
        return Result.success(service.list());
    }

    @GetMapping({"/cuisine/dish/list", "/cuisine/dish/list/{n}"})
    @ResponseBody
    public Result<PagerData> getDishList(
            @PathVariable(required = false) Integer n,
            Integer cuisineId
    ) {
        Page<Dish> page = service.getDishListByCuisine(n, cuisineId);
        Pager pager = new Pager(page, "/cuisine/dish/list");
        List<Dish> records = page.getRecords();
        List<DishDetails> list = dishService.getDishDetailsList(records);
        pager.setTailAppend("?cuisineId=" + cuisineId);
        return Result.success(new PagerData(pager, list));
    }
}

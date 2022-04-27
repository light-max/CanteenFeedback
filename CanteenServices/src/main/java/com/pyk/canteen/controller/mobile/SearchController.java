package com.pyk.canteen.controller.mobile;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pyk.canteen.model.data.Pager;
import com.pyk.canteen.model.data.PagerData;
import com.pyk.canteen.model.data.Result;
import com.pyk.canteen.model.entity.Dish;
import com.pyk.canteen.model.entity.Stall;
import com.pyk.canteen.model.result.DishDetails;
import com.pyk.canteen.model.result.StallDetails;
import com.pyk.canteen.service.DishService;
import com.pyk.canteen.service.StallService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller("mobile.search")
public class SearchController {
    @Resource
    DishService dishService;

    @Resource
    StallService stallService;

    @GetMapping("/search/dish/{n}")
    @ResponseBody
    public Result<PagerData> searchDish(
            @PathVariable(required = false) Integer n,
            String value
    ) {
        Page<Dish> page = dishService.search(n, value);
        List<Dish> records = page.getRecords();
        List<DishDetails> list = dishService.getDishDetailsList(records);
        Pager pager = new Pager(page, "/search/dish");
        pager.setTailAppend("?value=" + value);
        return Result.success(new PagerData(pager, list));
    }

    @GetMapping("/search/stall")
    @ResponseBody
    public Result<List<StallDetails>> searchStall(String value) {
        List<Stall> list = stallService.search(value);
        List<StallDetails> details = new ArrayList<>();
        for (Stall stall : list) {
            details.add(stallService.getStallDetails(stall.getId()));
        }
        return Result.success(details);
    }
}

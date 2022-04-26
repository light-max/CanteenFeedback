package com.pyk.canteen.controller.mobile;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pyk.canteen.model.data.Pager;
import com.pyk.canteen.model.data.PagerData;
import com.pyk.canteen.model.data.Result;
import com.pyk.canteen.model.entity.Account;
import com.pyk.canteen.model.entity.Collect;
import com.pyk.canteen.model.result.Collector;
import com.pyk.canteen.model.result.DishDetails;
import com.pyk.canteen.service.CollectService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller("mobile.collect")
public class CollectController {
    @Resource
    CollectService service;

    @PostMapping("/api/collect")
    @ResponseBody
    public Result<Boolean> collect(
            @SessionAttribute("account") Account account,
            Integer dishId
    ) {
        return Result.success(service.collect(account.getUid(), dishId) != null);
    }

    @GetMapping("/api/collect/check")
    @ResponseBody
    public Result<Boolean> check(
            @SessionAttribute("account") Account account,
            Integer dishId
    ) {
        return Result.success(service.check(account.getUid(), dishId) != null);
    }

    @GetMapping("/collect/count")
    @ResponseBody
    public Result<Integer> count(Integer dishId) {
        return Result.success(service.count(dishId));
    }

    @GetMapping({"/collect/list", "/collect/list/{n}"})
    @ResponseBody
    public Result<PagerData> list(
            @PathVariable(required = false) Integer n,
            Integer dishId
    ) {
        Page<Collect> page = service.list(n, dishId);
        Pager pager = new Pager(page, "/collect/list");
        pager.setTailAppend("?dishId=" + dishId);
        List<Collector> list = service.getCollector(page.getRecords());
        return Result.success(new PagerData(pager, list));
    }

    @GetMapping("/api/collect/list/all")
    @ResponseBody
    public Result<List<DishDetails>> getCollectAll(
            @SessionAttribute("account") Account account
    ) {
        return Result.success(service.getCollectAll(account.getUid()));
    }
}

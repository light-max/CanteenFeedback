package com.pyk.canteen.controller.canteen;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pyk.canteen.controller.ResourceController;
import com.pyk.canteen.mapper.CuisineMapper;
import com.pyk.canteen.model.data.Pager;
import com.pyk.canteen.model.data.Result;
import com.pyk.canteen.model.entity.Cuisine;
import com.pyk.canteen.model.entity.Dish;
import com.pyk.canteen.model.entity.Stall;
import com.pyk.canteen.model.result.DishDetails;
import com.pyk.canteen.model.result.Images;
import com.pyk.canteen.service.DishService;
import com.pyk.canteen.service.StallService;
import com.pyk.canteen.util.ump.ViewModelParameter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class DishController {
    @Resource
    DishService service;

    @Resource
    StallService stallService;

    @Resource
    CuisineMapper cuisineMapper;

    @Resource
    ResourceController resourceController;

    @GetMapping({"/canteen/dish", "/canteen/dish/list", "/canteen/dish/list/{n}"})
    @ViewModelParameter(key = "view", value = "dish")
    public String list(
            Model model,
            @RequestParam(required = false) Integer stallId,
            @PathVariable(required = false) Integer n
    ) {
        if (stallId != null) {
            model.addAttribute("stall", stallService.getById(stallId));
        }
        Page<Dish> page = service.query(stallId, n);
        List<Dish> list = page.getRecords();
        Pager pager = new Pager(page, "/canteen/dish/list");
        if (stallId != null) {
            pager.setTailAppend("?stallId=" + stallId);
        }
        model.addAttribute("stallId", stallId);
        model.addAttribute("stalls", stallService.list(true));
        model.addAttribute("list", service.getDishTD(list));
        model.addAttribute("pager", pager);
        return "/canteen/dish";
    }

    @GetMapping({"/canteen/dish/add/{stallId}"})
    @ViewModelParameter(key = "view", value = "dish")
    public String add(Model model, @PathVariable Integer stallId) {
        model.addAttribute("stall", stallService.getById(stallId));
        model.addAttribute("cuisines", cuisineMapper.selectList(Wrappers.emptyWrapper()));
        return "/canteen/dish_add";
    }

    @PostMapping({"/canteen/dish"})
    @ResponseBody
    public Result<Dish> add(
            Integer stallId, String name,
            Integer cuisine, String des,
            String material
    ) {
        return Result.success(service.add(stallId, name, cuisine, des, material));
    }

    @GetMapping({"/canteen/dish/update/{id}"})
    @ViewModelParameter(key = "view", value = "dish")
    public String update(Model model, @PathVariable Integer id) {
        Dish dish = service.getById(id);
        Stall stall = stallService.getById(dish.getSid());
        List<Cuisine> cuisines = cuisineMapper.selectList(Wrappers.emptyWrapper());
        Images images = resourceController.getImages("dish", id).getData();
        model.addAttribute("dish", dish);
        model.addAttribute("stall", stall);
        model.addAttribute("cuisines", cuisines);
        model.addAttribute("images", images);
        return "/canteen/dish_update";
    }

    @PutMapping({"/canteen/dish"})
    @ResponseBody
    public Result<Dish> update(
            Integer id, String name, Integer cid,
            String des, String material, Boolean enable
    ) {
        return Result.success(service.update(id, name, cid, des, material, enable));
    }

    @GetMapping("/dish/details/{id}")
    @ResponseBody
    public Result<DishDetails> getDetails(@PathVariable Integer id) {
        return Result.success(service.getDishDetails(id));
    }
}

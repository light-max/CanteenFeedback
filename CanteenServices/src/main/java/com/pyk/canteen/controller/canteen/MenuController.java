package com.pyk.canteen.controller.canteen;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pyk.canteen.model.data.Pager;
import com.pyk.canteen.model.data.PagerData;
import com.pyk.canteen.model.data.Result;
import com.pyk.canteen.model.entity.Dish;
import com.pyk.canteen.model.entity.Menu;
import com.pyk.canteen.model.entity.MenuItem;
import com.pyk.canteen.model.td.DishTD;
import com.pyk.canteen.model.td.MenuItemTD;
import com.pyk.canteen.service.DishService;
import com.pyk.canteen.service.MenuService;
import com.pyk.canteen.util.UseDefaultSuccessResponse;
import com.pyk.canteen.util.ump.ViewModelParameter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller("canteen.menu")
public class MenuController {
    @Resource
    MenuService service;

    @Resource
    DishService dishService;

    @GetMapping("/canteen/menu")
    @ViewModelParameter(key = "view", value = "menu")
    public String menu(Model model, @RequestParam(required = false) String d) {
        Menu menu = service.getMenuByDateNotNull(d);
        model.addAttribute("menu", menu);
        return "/canteen/menu";
    }

    @GetMapping({
            "/canteen/menu/item/list",
            "/canteen/menu/item/list/{n}",
            "/menu/item/list",
            "/menu/item/list/{n}"})
    @ResponseBody
    public Result<PagerData> list(
            @PathVariable(required = false) Integer n,
            Integer menuId
    ) {
        Page<MenuItem> page = service.getMenuItemListByMenuId(n, menuId);
        List<MenuItem> records = page.getRecords();
        List<MenuItemTD> list = service.getMenuItemTD(records);
        Pager pager = new Pager(page, "/canteen/menu/item/list");
        pager.setTailAppend("?menuId=" + menuId);
        return Result.success(new PagerData(pager, list));
    }

    @GetMapping({"/menu/today/list", "/menu/today/list/{n}"})
    @ResponseBody
    public Result<PagerData> todayList(@PathVariable(required = false) Integer n) {
        return list(n, service.currentMenu().getId());
    }

    @PostMapping("/canteen/menu/item")
    @ResponseBody
    public Result<MenuItemTD> addItem(Integer menuId, Integer dishId) {
        MenuItem item = service.addMenuItem(menuId, dishId);
        return Result.success(service.getMenuItemTD(item));
    }

    @PostMapping("/canteen/menu/item/list")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void addItemList(Integer menuId, Integer[] dishIds) {
        service.addMenuItems(menuId, dishIds);
    }

    @DeleteMapping("/canteen/menu/item")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void deleteMenuItem(Integer id) {
        service.removeMenuItem(id);
    }

    @GetMapping({"/canteen/menu/item/count", "/menu/item/count"})
    @ResponseBody
    public Result<Integer> itemCount(Integer menuId) {
        return Result.success(service.getMenuItemCount(menuId));
    }

    /**
     * 获取未被menuId添加过的菜品
     */
    @GetMapping({"/canteen/addable/dish/list", "/canteen/addable/dish/list/{n}"})
    @ResponseBody
    public Result<PagerData> getAddableDishList(
            @PathVariable(required = false) Integer n,
            Integer menuId
    ) {
        Page<Dish> page = service.getAddableDishList(n, menuId);
        List<Dish> records = page.getRecords();
        List<DishTD> list = dishService.getDishTD(records);
        Pager pager = new Pager(page, "/canteen/addable/dish/list");
        pager.setTailAppend("?menuId=" + menuId);
        return Result.success(new PagerData(pager, list));
    }
}

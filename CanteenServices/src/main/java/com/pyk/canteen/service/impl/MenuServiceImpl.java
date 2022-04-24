package com.pyk.canteen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pyk.canteen.constant.GlobalConstant;
import com.pyk.canteen.controller.ResourceController;
import com.pyk.canteen.mapper.MenuItemMapper;
import com.pyk.canteen.mapper.MenuMapper;
import com.pyk.canteen.mapper.StallMapper;
import com.pyk.canteen.model.entity.Dish;
import com.pyk.canteen.model.entity.Menu;
import com.pyk.canteen.model.entity.MenuItem;
import com.pyk.canteen.model.entity.Stall;
import com.pyk.canteen.model.result.Images;
import com.pyk.canteen.model.td.MenuItemTD;
import com.pyk.canteen.service.DishService;
import com.pyk.canteen.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class MenuServiceImpl implements MenuService {
    @Resource
    MenuMapper mapper;

    @Resource
    MenuItemMapper itemMapper;

    @Resource
    DishService dishService;

    @Resource
    ResourceController resourceController;

    @Resource
    StallMapper stallMapper;

    @Override
    public Menu currentMenu() {
        String dateString = LocalDate.now().toString();
        return getMenuByDateNotNull(dateString);
    }

    @Override
    public Menu getMenuByDate(String date) {
        return mapper.selectOne(new QueryWrapper<Menu>()
                .lambda()
                .eq(Menu::getDate, date));
    }

    @Override
    public Menu getMenuByDateNotNull(String nullableDate) {
        if (nullableDate == null) {
            nullableDate = LocalDate.now().toString();
        }
        Menu menu = getMenuByDate(nullableDate);
        if (menu == null) {
            menu = Menu.builder()
                    .date(nullableDate)
                    .build()
                    .check();
            mapper.insert(menu);
        }
        return menu;
    }

    @Override
    public MenuItem addMenuItem(Integer menuId, Integer dishId) {
        Menu menu = mapper.selectById(menuId);
        GlobalConstant.dataNotExists.notNull(menu);
        MenuItem item = itemMapper.selectOne(new QueryWrapper<MenuItem>()
                .lambda()
                .eq(MenuItem::getMenuId, menuId)
                .eq(MenuItem::getDishId, dishId)
        );
        GlobalConstant.menuItemExists.isNull(item);
        item = MenuItem.builder()
                .menuId(menuId)
                .dishId(dishId)
                .build();
        itemMapper.insert(item);
        return item;
    }

    @Override
    public void addMenuItems(Integer menuId, Integer[] dishIds) {
        if (dishIds != null) {
            for (Integer dishId : dishIds) {
                addMenuItem(menuId, dishId);
            }
        }
    }

    @Override
    public Page<Dish> getAddableDishList(Integer n, Integer menuId) {
        Menu menu = mapper.selectById(menuId);
        GlobalConstant.dataNotExists.notNull(menu);
        List<MenuItem> list = itemMapper.selectList(new QueryWrapper<MenuItem>()
                .lambda()
                .eq(MenuItem::getMenuId, menuId));
        IntStream stream = list.stream().mapToInt(MenuItem::getDishId);
        List<Integer> dishIds = new ArrayList<>();
        stream.forEach(dishIds::add);
        Page<Dish> page = new Page<>(n == null ? 0 : n, 8);
        LambdaQueryWrapper<Dish> w = new QueryWrapper<Dish>()
                .lambda()
                .eq(Dish::getEnable, true);
        if (!dishIds.isEmpty()) {
            w.not(wrapper -> wrapper.in(Dish::getId, dishIds));
        }
        return dishService.page(page, w);
    }

    @Override
    public Page<MenuItem> getMenuItemListByMenuId(Integer n, Integer menuId) {
        Page<MenuItem> page = new Page<>(n == null ? 0 : n, 12);
        LambdaQueryWrapper<MenuItem> wrapper = new QueryWrapper<MenuItem>()
                .lambda()
                .eq(MenuItem::getMenuId, menuId);
        return itemMapper.selectPage(page, wrapper);
    }

    @Override
    public List<MenuItemTD> getMenuItemTD(List<MenuItem> list) {
        return new ArrayList<MenuItemTD>() {{
            for (MenuItem item : list) {
                add(getMenuItemTD(item));
            }
        }};
    }

    @Override
    public MenuItemTD getMenuItemTD(MenuItem item) {
        Dish dish = dishService.getById(item.getDishId());
        GlobalConstant.dataNotExists.notNull(dish);
        Stall stall = stallMapper.selectById(dish.getSid());
        GlobalConstant.dataNotExists.notNull(stall);
        Images images = resourceController.getImages("dish", dish.getId()).getData();
        List<String> urls = images.getUrls();
        return MenuItemTD.builder()
                .id(item.getId())
                .menuId(item.getMenuId())
                .dishId(item.getDishId())
                .dishName(dish.getName())
                .dishCover(urls.isEmpty() ? "/images/no-image.jpg" : urls.get(0))
                .dishDes(dish.getDes())
                .dishMaterial(dish.getMaterial())
                .stallId(stall.getId())
                .stallName(stall.getName())
                .build();
    }

    @Override
    public int getMenuItemCount(Integer menuId) {
        return itemMapper.selectCount(new QueryWrapper<MenuItem>()
                .lambda()
                .eq(MenuItem::getMenuId, menuId));
    }

    @Override
    public void removeMenuItem(Integer id) {
        itemMapper.deleteById(id);
    }
}

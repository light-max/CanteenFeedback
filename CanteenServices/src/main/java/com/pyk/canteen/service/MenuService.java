package com.pyk.canteen.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pyk.canteen.model.entity.Dish;
import com.pyk.canteen.model.entity.Menu;
import com.pyk.canteen.model.entity.MenuItem;
import com.pyk.canteen.model.td.MenuItemTD;

import java.util.List;

public interface MenuService {
    Menu currentMenu();

    Menu getMenuByDate(String date);

    Menu getMenuByDateNotNull(String nullableDate);

    MenuItem addMenuItem(Integer menuId, Integer dishId);

    void addMenuItems(Integer menuId, Integer[] dishIds);

    Page<Dish> getAddableDishList(Integer n, Integer menuId);

    Page<MenuItem> getMenuItemListByMenuId(Integer n, Integer menuId);

    List<MenuItemTD> getMenuItemTD(List<MenuItem> list);

    MenuItemTD getMenuItemTD(MenuItem item);

    int getMenuItemCount(Integer menuId);

    void removeMenuItem(Integer id);
}

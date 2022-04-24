package com.pky.canteen.data.result;

public class MenuItem {
    private Integer id;
    private Integer menuId;
    private Integer dishId;
    private Integer stallId;
    private String dishName;
    private String dishDes;
    private String dishMaterial;
    private String dishCover;
    private String stallName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public Integer getStallId() {
        return stallId;
    }

    public void setStallId(Integer stallId) {
        this.stallId = stallId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getDishDes() {
        return dishDes;
    }

    public void setDishDes(String dishDes) {
        this.dishDes = dishDes;
    }

    public String getDishMaterial() {
        return dishMaterial;
    }

    public void setDishMaterial(String dishMaterial) {
        this.dishMaterial = dishMaterial;
    }

    public String getDishCover() {
        return dishCover;
    }

    public void setDishCover(String dishCover) {
        this.dishCover = dishCover;
    }

    public String getStallName() {
        return stallName;
    }

    public void setStallName(String stallName) {
        this.stallName = stallName;
    }
}

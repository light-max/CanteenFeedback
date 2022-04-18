package com.pyk.canteen.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pyk.canteen.model.entity.Collect;
import com.pyk.canteen.model.result.Collector;

import java.util.List;

public interface CollectService extends IService<Collect> {
    Collect collect(String uid, Integer dishId);

    Collect check(String uid, Integer dishId);

    Integer count(Integer dishId);

    Page<Collect> list(Integer n, Integer dishId);

    List<Collector> getCollector(List<Collect> list);
}

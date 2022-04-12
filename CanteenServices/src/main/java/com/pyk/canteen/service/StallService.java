package com.pyk.canteen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pyk.canteen.model.entity.Stall;

import java.util.List;

public interface StallService extends IService<Stall> {
    List<Stall> list(Boolean enable);

    Stall add(String name, String des);

    Stall update(Integer id, String name, String des, Boolean enable);
}

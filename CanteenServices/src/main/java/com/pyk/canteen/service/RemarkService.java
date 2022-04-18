package com.pyk.canteen.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pyk.canteen.model.entity.Account;
import com.pyk.canteen.model.entity.Remark;
import com.pyk.canteen.model.result.RemarkR;

import java.util.List;

public interface RemarkService extends IService<Remark> {
    Remark addRemark(Account account, Integer dishId, String content, Integer parentId);

    Page<Remark> getByDishId(Integer n, Integer dishId);

    Page<Remark> getByParentId(Integer n, Integer parentId);

    List<RemarkR> get(List<Remark> list);

    int count(Integer dishId);
}

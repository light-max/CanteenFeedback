package com.pyk.canteen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pyk.canteen.constant.GlobalConstant;
import com.pyk.canteen.mapper.*;
import com.pyk.canteen.model.entity.Account;
import com.pyk.canteen.model.entity.Remark;
import com.pyk.canteen.model.result.RemarkR;
import com.pyk.canteen.service.RemarkService;
import com.pyk.canteen.util.datetranslate.DefaultDataTranslate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RemarkServiceImpl extends ServiceImpl<RemarkMapper, Remark> implements RemarkService {
    @Resource
    DishMapper dishMapper;

    @Resource
    AccountMapper accountMapper;

    @Resource
    TeacherMapper teacherMapper;

    @Resource
    StudentMapper studentMapper;

    @Override
    public Remark addRemark(Account account, Integer dishId, String content, Integer parentId) {
        GlobalConstant.dataNotExists.notNull(dishMapper.selectById(dishId));
        if (parentId != null) {
            GlobalConstant.dataNotExists.notNull(getById(parentId));
        }
        Remark remark = Remark.builder()
                .content(content)
                .dishId(dishId)
                .createById(account.getUid())
                .parentId(parentId)
                .build()
                .check();
        save(remark);
        return remark;
    }

    @Override
    public Page<Remark> getByDishId(Integer n, Integer dishId) {
        return page(new Page<>(n == null ? 0 : n, 10),
                new QueryWrapper<Remark>()
                        .lambda()
                        .eq(Remark::getDishId, dishId)
                        .isNull(Remark::getParentId)
                        .orderByDesc(Remark::getCreateTime)
        );
    }

    @Override
    public Page<Remark> getByParentId(Integer n, Integer parentId) {
        return page(new Page<>(n == null ? 0 : n, 8),
                new QueryWrapper<Remark>()
                        .lambda()
                        .eq(Remark::getParentId, parentId)
                        .orderByDesc(Remark::getCreateTime)
        );
    }

    @Override
    public List<RemarkR> get(List<Remark> list) {
        return new ArrayList<RemarkR>() {{
            for (Remark r : list) {
                String name = r.getCreateById();
                Account account = accountMapper.selectById(name);
                if (account.getType() == 2) {
                    name = teacherMapper.selectById(account.getUid()).getName();
                } else if (account.getType() == 3) {
                    name = studentMapper.selectById(account.getUid()).getName();
                }
                int count = count(new QueryWrapper<Remark>()
                        .lambda()
                        .eq(Remark::getParentId, r.getId()));
                add(RemarkR.builder()
                        .id(r.getId())
                        .dishId(r.getDishId())
                        .createById(r.getCreateById())
                        .parentId(r.getParentId())
                        .content(r.getContent())
                        .createTime(r.getCreateTime())
                        .createByHead("/head/" + r.getCreateById())
                        .createByName(name)
                        .createTimeText(new DefaultDataTranslate(r.getCreateTime()).translateDate())
                        .replyCount(count)
                        .build());
            }
        }};
    }

    @Override
    public int count(Integer dishId) {
        return count(new QueryWrapper<Remark>()
                .lambda()
                .eq(Remark::getDishId, dishId)
                .isNull(Remark::getParentId));
    }
}

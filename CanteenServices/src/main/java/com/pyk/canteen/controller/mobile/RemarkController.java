package com.pyk.canteen.controller.mobile;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pyk.canteen.constant.GlobalConstant;
import com.pyk.canteen.model.data.Pager;
import com.pyk.canteen.model.data.PagerData;
import com.pyk.canteen.model.data.Result;
import com.pyk.canteen.model.entity.Account;
import com.pyk.canteen.model.entity.Remark;
import com.pyk.canteen.model.result.RemarkR;
import com.pyk.canteen.service.RemarkService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller("mobile.remark")
public class RemarkController {
    @Resource
    RemarkService service;

    @PostMapping("/api/remark")
    @ResponseBody
    public Result<Remark> addRemark(
            @SessionAttribute("account") Account account,
            @RequestParam(required = false) Integer parentId,
            Integer dishId, String content
    ) {
        return Result.success(service.addRemark(account, dishId, content, parentId));
    }

    @GetMapping({"/remark/list", "/remark/list/{n}"})
    @ResponseBody
    public Result<PagerData> list(
            @PathVariable(required = false) Integer n,
            @RequestParam(required = false) Integer parentId,
            @RequestParam(required = false) Integer dishId
    ) {
        Page<Remark> page = null;
        if (parentId != null) {
            page = service.getByParentId(n, parentId);
        } else if (dishId != null) {
            page = service.getByDishId(n, dishId);
        }
        GlobalConstant.dataNotExists.notNull(page);
        assert page != null;
        List<Remark> records = page.getRecords();
        List<RemarkR> list = service.get(records);
        Pager pager = new Pager(page, "/remark/list");
        if (parentId != null) {
            pager.setTailAppend("?parentId=" + parentId);
        }
        if (dishId != null) {
            pager.setTailAppend("?dishId=" + dishId);
        }
        return Result.success(new PagerData(pager, list));
    }

    @GetMapping("/remark/count")
    @ResponseBody
    public Result<Integer> count(Integer dishId) {
        return Result.success(service.count(dishId));
    }
}

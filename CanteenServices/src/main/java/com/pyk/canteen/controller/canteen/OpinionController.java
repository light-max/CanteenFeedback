package com.pyk.canteen.controller.canteen;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pyk.canteen.model.data.Pager;
import com.pyk.canteen.model.entity.Opinion;
import com.pyk.canteen.model.td.OpinionTD;
import com.pyk.canteen.service.OpinionService;
import com.pyk.canteen.util.ump.ViewModelParameter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller("canteen.opinion")
public class OpinionController {
    @Resource
    OpinionService service;

    /**
     * @param status 0:待反馈,1:已反馈,2:全部, 默认0
     */
    @GetMapping({"/canteen/opinion", "/canteen/opinion/list", "/canteen/opinion/list/{n}"})
    @ViewModelParameter(key = "view", value = "opinion")
    public String list(
            Model model,
            @PathVariable(required = false) Integer n,
            @RequestParam(defaultValue = "0") Integer status
    ) {
        Page<Opinion> page = service.queryByStatus(n, status);
        List<Opinion> records = page.getRecords();
        List<OpinionTD> list = service.get(records);
        Pager pager = new Pager(page, "/canteen/opinion/list");
        pager.setTailAppend("?status=" + status);
        model.addAttribute("status", status);
        model.addAttribute("list", list);
        model.addAttribute("pager", pager);
        return "/canteen/opinion";
    }
}

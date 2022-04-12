package com.pyk.canteen.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pyk.canteen.model.data.Pager;
import com.pyk.canteen.model.entity.Complaint;
import com.pyk.canteen.service.ComplaintService;
import com.pyk.canteen.util.UseDefaultSuccessResponse;
import com.pyk.canteen.util.ump.ViewModelParameter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller("admin.complaint")
public class ComplaintController {
    @Resource
    ComplaintService service;

    /**
     * @param status 0:待处理,1:已处理,2:全部, 默认0
     */
    @GetMapping({"/admin/complaint", "/admin/complaint/list", "/admin/complaint/list/{n}"})
    @ViewModelParameter(key = "view", value = "complaint")
    public String list(
            Model model,
            @PathVariable(required = false) Integer n,
            @RequestParam(defaultValue = "0") Integer status
    ) {
        Page<Complaint> page = service.list(n, status);
        List<Complaint> records = page.getRecords();
        Pager pager = new Pager(page, "/admin/complaint/list");
        pager.setTailAppend("?status=" + status);
        model.addAttribute("status", status);
        model.addAttribute("list", records);
        model.addAttribute("pager", pager);
        return "/admin/complaint";
    }

    @GetMapping("/admin/complaint/details/{id}")
    @ViewModelParameter(key = "view", value = "complaint")
    public String details(Model model, @PathVariable Integer id) {
        model.addAttribute("c", service.details(id));
        return "/admin/complaint_details";
    }

    @PostMapping("/admin/complaint/execute")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void execute(Integer id, String result) {
        service.execute(id, result);
    }
}

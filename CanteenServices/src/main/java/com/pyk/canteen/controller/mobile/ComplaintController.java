package com.pyk.canteen.controller.mobile;

import com.pyk.canteen.model.data.Result;
import com.pyk.canteen.model.entity.Account;
import com.pyk.canteen.model.entity.Complaint;
import com.pyk.canteen.model.td.ComplaintDetailsTD;
import com.pyk.canteen.service.ComplaintService;
import com.pyk.canteen.util.UseDefaultSuccessResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller("mobile.complaint")
public class ComplaintController {
    @Resource
    ComplaintService service;

    @PostMapping("/api/complaint")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void post(
            @SessionAttribute("account") Account account,
            Integer feedbackId, String des
    ) {
        service.post(account.getUid(), feedbackId, des);
    }

    @GetMapping("/api/complaint")
    @ResponseBody
    public Result<ComplaintDetailsTD> getComplaint(
            @SessionAttribute("account") Account account,
            Integer feedbackId
    ) {
        Complaint complaint = service.getByFeedbackId(feedbackId);
        if (complaint == null) {
            return Result.success();
        }
        return Result.success(service.details(complaint.getId()));
    }

    @GetMapping("/api/complaint/list/all")
    @ResponseBody
    public Result<List<ComplaintDetailsTD>> getListAll(
            @SessionAttribute("account") Account account
    ) {
        List<Complaint> all = service.getListAllByUid(account.getUid());
        List<ComplaintDetailsTD> list = new ArrayList<>();
        for (Complaint complaint : all) {
            list.add(service.details(complaint.getId()));
        }
        return Result.success(list);
    }
}

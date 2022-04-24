package com.pyk.canteen.controller.mobile;

import com.pyk.canteen.model.entity.Account;
import com.pyk.canteen.service.ComplaintService;
import com.pyk.canteen.util.UseDefaultSuccessResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.annotation.Resource;

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
}

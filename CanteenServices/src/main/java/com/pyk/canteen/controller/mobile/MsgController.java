package com.pyk.canteen.controller.mobile;

import com.pyk.canteen.model.data.Result;
import com.pyk.canteen.model.entity.Account;
import com.pyk.canteen.model.result.FeedbackMsgDetails;
import com.pyk.canteen.service.FeedbackService;
import com.pyk.canteen.util.UseDefaultSuccessResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller("mobile.msg")
public class MsgController {
    @Resource
    FeedbackService service;

    @GetMapping("/api/feedback/msg/all")
    @ResponseBody
    public Result<List<FeedbackMsgDetails>> getFeedbackMsgAll(
            @SessionAttribute("account") Account account
    ) {
        return Result.success(service.getMsgListByUid(account.getUid()));
    }

    @PostMapping("/api/feedback/msg/read/{id}")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void readFeedbackMsg(
            @SessionAttribute("account") Account account,
            @PathVariable Integer id
    ) {
        service.readMsg(id, account.getUid());
    }

    @PostMapping("/api/feedback/msg/unread/{id}")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void unreadFeedbackMsg(
            @SessionAttribute("account") Account account,
            @PathVariable Integer id
    ) {
        service.unreadMsg(id, account.getUid());
    }
}

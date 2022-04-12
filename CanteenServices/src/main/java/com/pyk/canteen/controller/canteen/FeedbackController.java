package com.pyk.canteen.controller.canteen;

import com.pyk.canteen.constant.GlobalConstant;
import com.pyk.canteen.model.data.Result;
import com.pyk.canteen.model.entity.Feedback;
import com.pyk.canteen.service.FeedbackService;
import com.pyk.canteen.util.UseDefaultSuccessResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller("canteen.feedback")
public class FeedbackController {
    @Resource
    FeedbackService service;

    @PostMapping("/canteen/feedback")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void post(Integer opinionId, String content) {
        service.post(opinionId, content);
    }

    @GetMapping("/canteen/feedback/{id}")
    @ResponseBody
    public Result<Feedback> get(@PathVariable Integer id) {
        Feedback feedback = service.getById(id);
        GlobalConstant.dataNotExists.notNull(feedback);
        return Result.success(feedback);
    }
}

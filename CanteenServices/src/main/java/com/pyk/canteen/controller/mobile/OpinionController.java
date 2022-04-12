package com.pyk.canteen.controller.mobile;

import com.pyk.canteen.model.data.Result;
import com.pyk.canteen.model.entity.Account;
import com.pyk.canteen.model.result.COpinion;
import com.pyk.canteen.service.OpinionService;
import com.pyk.canteen.util.UseDefaultSuccessResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class OpinionController {
    @Resource
    OpinionService service;

    @PostMapping("/api/opinion")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void post(
            @SessionAttribute("account") Account account,
            Integer dishId, String content,
            MultipartFile[] images,
            MultipartFile video
    ) {
        List<MultipartFile> files = new ArrayList<>();
        if (images != null) {
            files.addAll(Arrays.asList(images));
        }
        service.post(account, dishId, content, files, video);
    }

    @GetMapping("/api/opinion/list")
    @ResponseBody
    public Result<List<COpinion>> list(
            @SessionAttribute("account") Account account,
            @RequestParam(required = false) Integer dishId
    ) {
        return Result.success(service.list(account, dishId));
    }
}

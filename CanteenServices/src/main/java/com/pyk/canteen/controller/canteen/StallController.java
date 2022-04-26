package com.pyk.canteen.controller.canteen;

import com.pyk.canteen.controller.ResourceController;
import com.pyk.canteen.model.data.Result;
import com.pyk.canteen.model.entity.Stall;
import com.pyk.canteen.model.result.Images;
import com.pyk.canteen.model.result.StallDetails;
import com.pyk.canteen.service.StallService;
import com.pyk.canteen.util.ump.ViewModelParameter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class StallController {
    @Resource
    StallService service;

    @Resource
    ResourceController resourceController;

    @GetMapping({"/canteen/stall", "/canteen/stall/list"})
    @ViewModelParameter(key = "view", value = "stall")
    public String list(
            Model model,
            @RequestParam(required = false) Boolean enable
    ) {
        model.addAttribute("list", service.list(enable));
        model.addAttribute("status", enable);
        return "/canteen/stall";
    }

    @GetMapping("/stall/list/all")
    @ResponseBody
    public Result<List<StallDetails>> getAllStall() {
        return Result.success(service.getAllStall());
    }

    @GetMapping("/stall/details/{id}")
    @ResponseBody
    public Result<StallDetails> getDetails(@PathVariable Integer id) {
        return Result.success(service.getStallDetails(id));
    }

    @GetMapping("/canteen/stall/add")
    @ViewModelParameter(key = "view", value = "stall")
    public String add(Model model) {
        return "/canteen/stall_add";
    }

    @PostMapping("/canteen/stall")
    @ResponseBody
    public Result<Stall> add(String name, String des) {
        return Result.success(service.add(name, des));
    }

    @GetMapping("/canteen/stall/update/{id}")
    @ViewModelParameter(key = "view", value = "stall")
    public String update(Model model, @PathVariable Integer id) {
        Stall stall = service.getById(id);
        Images images = resourceController.getImages("stall", id).getData();
        model.addAttribute("stall", stall);
        model.addAttribute("images", images);
        return "/canteen/stall_update";
    }

    @PutMapping("/canteen/stall")
    @ResponseBody
    public Result<Stall> update(
            Integer id, String name,
            String des, Boolean enable
    ) {
        return Result.success(service.update(id, name, des, enable));
    }
}



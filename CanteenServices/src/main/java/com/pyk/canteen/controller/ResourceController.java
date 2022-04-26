package com.pyk.canteen.controller;

import com.pyk.canteen.constant.AssertException;
import com.pyk.canteen.constant.GlobalConstant;
import com.pyk.canteen.model.data.Result;
import com.pyk.canteen.model.entity.Account;
import com.pyk.canteen.model.result.Images;
import com.pyk.canteen.util.FileTools;
import com.pyk.canteen.util.UseDefaultSuccessResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ResourceController {
    @Value("${file.images.stall}")
    private String stallImagesPath;

    @Value("${file.images.dish}")
    private String dishImagesPath;

    @Value("${file.video.opinion}")
    private String opinionVideosPath;

    @Value("${file.images.opinion}")
    private String opinionImagesPath;

    @Value("${file.images.head}")
    private String userHeadImagePath;

    /**
     * @param type dish stall opinion
     */
    private String getImagePathByType(String type) {
        if ("stall".equals(type)) return stallImagesPath;
        else if ("dish".equals(type)) return dishImagesPath;
        else if ("opinion".equals(type)) return opinionImagesPath;
        else throw new AssertException(GlobalConstant.error.getMessage());
    }

    @PostMapping({"/canteen/{type}/image/{id}"})
    @ResponseBody
    @UseDefaultSuccessResponse
    public void addImage(
            @PathVariable String type,
            @PathVariable Integer id,
            MultipartFile file
    ) throws IOException {
        File path = FileTools.makeImagePath(getImagePathByType(type), id);
        path.getParentFile().mkdirs();
        FileTools.write(file.getInputStream(), path);
    }

    @GetMapping(value = "/{type}/image/{id}/{index}", produces = "image/jpeg")
    @ResponseBody
    public ResponseEntity<FileSystemResource> getImage(
            @PathVariable String type,
            @PathVariable Integer id,
            @PathVariable Integer index
    ) {
        File file = FileTools.getImagePath(getImagePathByType(type), id, index);
        return ResponseEntity.ok(new FileSystemResource(file));
    }

    @DeleteMapping({"/canteen/{type}/image/{id}/{index}"})
    @ResponseBody
    @UseDefaultSuccessResponse
    public void deleteImage(
            @PathVariable String type,
            @PathVariable Integer id,
            @PathVariable Integer index
    ) {
        FileTools.getImagePath(getImagePathByType(type), id, index).delete();
    }

    @GetMapping({"/canteen/{type}/images/{id}"})
    @ResponseBody
    public Result<Images> getImages(@PathVariable String type, @PathVariable Integer id) {
        File path = FileTools.getImagePath(getImagePathByType(type), id);
        File[] files = path.listFiles();
        if (files == null) {
            return Result.success(Images.builder()
                    .id(id)
                    .urls(new ArrayList<>())
                    .build());
        }
        List<String> urls = new ArrayList<>();
        for (File file : files) {
            urls.add("/" + type + "/image/" + id + "/" + file.getName());
        }
        return Result.success(Images.builder()
                .id(id)
                .urls(urls)
                .build());
    }

    @PostMapping({"/s/head", "/t/head", "/api/head"})
    @ResponseBody
    @UseDefaultSuccessResponse
    public void setHeadImage(
            @SessionAttribute("account") Account account,
            MultipartFile file
    ) throws IOException {
        File path = FileTools.getHeadImagePath(userHeadImagePath, account.getUid());
        FileTools.write(file.getInputStream(), path);
    }

    @GetMapping("/head/raw/{uid}")
    @ResponseBody
    public ResponseEntity<FileSystemResource> getHeadImage(@PathVariable String uid) {
        File file = FileTools.getHeadImagePath(userHeadImagePath, uid);
        return ResponseEntity.ok(new FileSystemResource(file));
    }

    @GetMapping("/head/{uid}")
    public String getHead(@PathVariable String uid) {
        File file = FileTools.getHeadImagePath(userHeadImagePath, uid);
        if (file.exists()) {
            return "redirect:/head/raw/" + uid;
        } else {
            return "redirect:/images/default-head.jpg";
        }
    }

//    @GetMapping(value = "/opinion/video/{id}")
//    @ResponseBody
//    public ResponseEntity<FileSystemResource> getOpinionVideo(@PathVariable Integer id) {
//        File file = FileTools.getVideoFilePath(opinionVideosPath, id);
//        return ResponseEntity.ok(new FileSystemResource(file));
//    }

    @GetMapping(value = "/opinion/video/play/{id}")
    public String playVideo(@PathVariable Integer id) {
        return "/common/flvplay";
    }

    @GetMapping(value = "/opinion/video/{id}", produces = "video/mp4")
    @ResponseBody
    public void getOpinionVideo(HttpServletResponse response, @PathVariable Integer id) {
        try {
            File file = FileTools.getVideoFilePath(opinionVideosPath, id);
            if (file.exists()) {
                response.setContentType("video/mp4");
                response.setContentLengthLong(file.length());
                FileInputStream in = new FileInputStream(file);
                ServletOutputStream out = response.getOutputStream();
                byte[] bytes = new byte[1024 * 10];
                int len;
                while ((len = in.read(bytes)) != -1) {
                    out.write(bytes, 0, len);
                }
                response.flushBuffer();
            } else {
                response.setStatus(HttpStatus.NOT_FOUND.value());
            }
        } catch (java.nio.file.NoSuchFileException e) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            e.printStackTrace();
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            e.printStackTrace();
        }
    }

}

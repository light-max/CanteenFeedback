package com.pyk.canteen.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pyk.canteen.model.entity.Account;
import com.pyk.canteen.model.entity.Opinion;
import com.pyk.canteen.model.result.COpinion;
import com.pyk.canteen.model.td.OpinionTD;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface OpinionService extends IService<Opinion> {
    Page<Opinion> queryByStatus(Integer n, int status);

    void post(Account account, Integer dishId, String content,
              List<MultipartFile> images, MultipartFile video
    );

    List<COpinion> list(Account account, Integer dishId);

    List<OpinionTD> get(List<Opinion> list);

    COpinion getDetails(Integer id);
}

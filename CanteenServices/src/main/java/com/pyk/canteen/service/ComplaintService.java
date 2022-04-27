package com.pyk.canteen.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pyk.canteen.model.entity.Complaint;
import com.pyk.canteen.model.td.ComplaintDetailsTD;

import java.util.List;

public interface ComplaintService extends IService<Complaint> {
    void post(String uid, Integer feedbackId, String des);

    Page<Complaint> list(Integer n, int status);

    ComplaintDetailsTD details(Integer id);

    void execute(Integer id, String result);

    Complaint getByFeedbackId(Integer feedbackId);

    List<Complaint> getListAllByUid(String uid);
}

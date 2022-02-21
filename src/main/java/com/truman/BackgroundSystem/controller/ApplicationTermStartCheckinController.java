package com.truman.BackgroundSystem.controller;


import cn.hutool.core.lang.UUID;
import com.truman.BackgroundSystem.Common.Result.Result;
import com.truman.BackgroundSystem.Common.Result.ResultUtils;
import com.truman.BackgroundSystem.entity.ApplicationAnnounce;
import com.truman.BackgroundSystem.entity.ApplicationTermStartCheckin;
import com.truman.BackgroundSystem.entity.StudentDetail;
import com.truman.BackgroundSystem.mapper.ApplicationAnnounceMapper;
import com.truman.BackgroundSystem.mapper.ApplicationTermStartCheckinMapper;
import com.truman.BackgroundSystem.mapper.StudentDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 冯有恒
 * @since 2022-02-06
 */
@RestController
@RequestMapping("/termStartCheckin")
public class ApplicationTermStartCheckinController {
    @Autowired
    ApplicationTermStartCheckinMapper termStartCheckinMapper;

    @Autowired
    ApplicationAnnounceMapper announceMapper;

    @Autowired
    StudentDetailMapper studentDetailMapper;

    @PutMapping
    public Result<?> addTermStartCheckin(@RequestBody ApplicationTermStartCheckin termStartCheckin) {
        try {
            if (studentDetailMapper.selectById(termStartCheckin.getApplicantId()).getInOut()) {
                return ResultUtils.Err(-1, "你已入住不可重复办理");
            } else {
                termStartCheckin.setApplicationId("kxrz_" + UUID.fastUUID().toString());
                termStartCheckinMapper.insert(termStartCheckin);
                announceMapper.insert(
                        new ApplicationAnnounce(
                                termStartCheckin.getApplicationId(),
                                termStartCheckin.getApplicantId(),
                                termStartCheckin.getApprovalId(),
                                false, null, null));
                return ResultUtils.success("提交成功");
            }

        } catch (Exception e) {
            return ResultUtils.Err(-1, e.getMessage());
        }
    }
}

package com.truman.BackgroundSystem.controller;


import cn.hutool.core.lang.UUID;
import com.truman.BackgroundSystem.Common.Result.Result;
import com.truman.BackgroundSystem.Common.Result.ResultUtils;
import com.truman.BackgroundSystem.entity.ApplicationAnnounce;
import com.truman.BackgroundSystem.entity.ApplicationTermFinishCheckout;
import com.truman.BackgroundSystem.entity.ApplicationTermStartCheckin;
import com.truman.BackgroundSystem.mapper.ApplicationAnnounceMapper;
import com.truman.BackgroundSystem.mapper.ApplicationTermFinishCheckoutMapper;
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
@RequestMapping("/termFinishCheckout")
public class ApplicationTermFinishCheckoutController {
    @Autowired
    ApplicationTermFinishCheckoutMapper termFinishCheckoutMapper;

    @Autowired
    ApplicationAnnounceMapper announceMapper;

    @Autowired
    StudentDetailMapper studentDetailMapper;

    @PutMapping
    public Result<?> addTermFinishCheckout(@RequestBody ApplicationTermFinishCheckout termFinishCheckout) {
        try {
            if (termFinishCheckoutMapper.getAllNotTermFinishCheckoutByApplicantId(termFinishCheckout.getApplicantId()) != null) {
                return ResultUtils.Err(-1, "不可重复办理");
            } else if (!studentDetailMapper.selectById(termFinishCheckout.getApplicantId()).getInOut()) {
                return ResultUtils.Err(-1, "你已离宿不可再办理离宿");
            } else {
                termFinishCheckout.setApplicationId("qmls_" + UUID.fastUUID().toString());
                termFinishCheckoutMapper.insert(termFinishCheckout);
                announceMapper.insert(
                        new ApplicationAnnounce(
                                termFinishCheckout.getApplicationId(),
                                termFinishCheckout.getApplicantId(),
                                termFinishCheckout.getApprovalId(),
                                false, null, null));
                return ResultUtils.success("提交成功");
            }
        } catch (Exception e) {
            return ResultUtils.Err(-1, e.getMessage());
        }
    }
}

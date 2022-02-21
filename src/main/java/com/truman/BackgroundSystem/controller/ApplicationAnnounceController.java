package com.truman.BackgroundSystem.controller;


import com.alibaba.fastjson.JSONObject;
import com.truman.BackgroundSystem.Common.Result.Result;
import com.truman.BackgroundSystem.Common.Result.ResultUtils;
import com.truman.BackgroundSystem.mapper.ApplicationAnnounceMapper;
import com.truman.BackgroundSystem.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 冯有恒
 * @since 2022-02-06
 */
@RestController
@RequestMapping("/announce")
public class ApplicationAnnounceController {
    @Autowired
    ApplicationAnnounceMapper announceMapper;

    @Autowired
    ApplicationAnnounceServiceImpl announceService;

    @Autowired
    ApplicationLateReturnServiceImpl lateReturnService;

    @Autowired
    ApplicationCheckoutServiceImpl checkoutService;

    @Autowired
    ApplicationRepairServiceImpl repairService;

    @Autowired
    ApplicationTermFinishCheckoutServiceImpl termFinishCheckoutService;

    @Autowired
    ApplicationTermStartCheckinServiceImpl termStartCheckinService;


    @GetMapping("/getTipsNum")
    public Result<?> getTipsNum(@RequestParam String id) {
        try {
            return ResultUtils.success(announceMapper.selNumsOfNotFinishAppByAnnounceId(id));
        } catch (Exception e) {
            return ResultUtils.Err(-1, e.getMessage());
        }
    }

    @GetMapping("/getSubmittedTaskList")
    public Result<?> getToBeHandledTaskList(@RequestParam String id) {
        try {
            return ResultUtils.success(announceService.selSubmittedTaskList(id));
        } catch (Exception e) {
            return ResultUtils.Err(-1, e.getMessage());
        }
    }

    @GetMapping("/getApprovedTaskList")
    public Result<?> getApprovedTaskList(@RequestParam String id) {
        try {
            return ResultUtils.success(announceService.selApprovedTaskList(id));
        } catch (Exception e) {
            return ResultUtils.Err(-1, e.getMessage());
        }
    }
    @GetMapping("/getNotApprovedTaskList")
    public Result<?> getNotApprovedTaskList(@RequestParam String id) {
        try {
            return ResultUtils.success(announceService.selNotApprovedTaskList(id));
        } catch (Exception e) {///TODO
            return ResultUtils.Err(-1, e.getMessage());
        }
    }
}

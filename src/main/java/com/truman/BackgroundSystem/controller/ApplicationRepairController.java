package com.truman.BackgroundSystem.controller;


import cn.hutool.core.lang.UUID;
import com.truman.BackgroundSystem.Common.Result.Result;
import com.truman.BackgroundSystem.Common.Result.ResultUtils;
import com.truman.BackgroundSystem.entity.ApplicationAnnounce;
import com.truman.BackgroundSystem.entity.ApplicationRepair;
import com.truman.BackgroundSystem.mapper.ApplicationAnnounceMapper;
import com.truman.BackgroundSystem.mapper.ApplicationRepairMapper;
import com.truman.BackgroundSystem.service.impl.ApplicationRepairServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 冯有恒
 * @since 2022-02-06
 */
@RestController
@RequestMapping("/repair")
public class ApplicationRepairController {

    @Autowired
    ApplicationAnnounceMapper announceMapper;

    @Autowired
    ApplicationRepairMapper repairMapper;

    @Autowired
    ApplicationRepairServiceImpl repairService;

    @GetMapping("/getAllRepairer")
    public Result<?> getAllRepairer() {
        try {
            return ResultUtils.success(repairService.getRepairerNameId());
        } catch (Exception e) {
            return ResultUtils.Err(-1, e.getMessage());
        }
    }

    @PutMapping("/insRepair")
    public Result<?> insRepair(@RequestBody ApplicationRepair repair) {
        try {
            System.out.println(repair);
            repair.setApplicationId("sdwx_" + UUID.fastUUID().toString());
            repairMapper.insert(repair);
            announceMapper.insert(
                    new ApplicationAnnounce(
                            repair.getApplicationId(),
                            repair.getApplicantId(),
                            repair.getApprovalId(),
                            false, null, null)
            );
            return ResultUtils.success("提交成功");
        } catch (Exception e) {
            return ResultUtils.Err(-1, e.getMessage());
        }
    }
}

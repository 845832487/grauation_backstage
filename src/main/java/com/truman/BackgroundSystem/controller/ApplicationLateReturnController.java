package com.truman.BackgroundSystem.controller;


import cn.hutool.core.lang.UUID;
import com.truman.BackgroundSystem.Common.Result.Result;
import com.truman.BackgroundSystem.Common.Result.ResultUtils;
import com.truman.BackgroundSystem.entity.ApplicationAnnounce;
import com.truman.BackgroundSystem.entity.ApplicationLateReturn;
import com.truman.BackgroundSystem.mapper.ApplicationAnnounceMapper;
import com.truman.BackgroundSystem.mapper.ApplicationLateReturnMapper;
import com.truman.BackgroundSystem.service.impl.ApplicationLateReturnServiceImpl;
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
@RequestMapping("/returnLate")
public class ApplicationLateReturnController {
    @Autowired
    ApplicationLateReturnMapper lateReturnMapper;

    @Autowired
    ApplicationAnnounceMapper announceMapper;

    @Autowired
    ApplicationLateReturnServiceImpl lateReturnService;

    @PutMapping("/insReturnLate")
    public Result<?> insReturnLate(@RequestBody ApplicationLateReturn lateReturn) {
        try {
            lateReturn.setApplicationId("wgsq_"+UUID.fastUUID().toString());
            lateReturnMapper.insert(lateReturn);
            announceMapper.insert(
                    new ApplicationAnnounce(
                            lateReturn.getApplicationId(),
                            lateReturn.getApplicantId(),
                            lateReturn.getApprovalId(),
                            false, null,null));
            return ResultUtils.success("提交表单成功");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResultUtils.Err(-1, e.getMessage());
        }


    }

    @GetMapping("/getAllDormKeeper")
    public Result<?> getAllDormKeeper(@RequestParam String id) {
        try {
            System.out.println(id);
            return ResultUtils.success(lateReturnService.getDormKeeperNameIdByStudentId(id));
        } catch (Exception e) {
            return ResultUtils.Err(-1, e.getMessage());
        }
    }
}

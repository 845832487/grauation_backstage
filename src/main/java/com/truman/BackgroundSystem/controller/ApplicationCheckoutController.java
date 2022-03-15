package com.truman.BackgroundSystem.controller;


import cn.hutool.core.lang.UUID;
import com.truman.BackgroundSystem.Common.Result.Result;
import com.truman.BackgroundSystem.Common.Result.ResultUtils;
import com.truman.BackgroundSystem.entity.ApplicationAnnounce;
import com.truman.BackgroundSystem.entity.ApplicationCheckout;
import com.truman.BackgroundSystem.mapper.ApplicationAnnounceMapper;
import com.truman.BackgroundSystem.mapper.ApplicationCheckoutMapper;
import com.truman.BackgroundSystem.mapper.StudentDetailMapper;
import com.truman.BackgroundSystem.mapper.WorkerDetailMapper;
import com.truman.BackgroundSystem.service.impl.ApplicationAnnounceServiceImpl;
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
@RequestMapping("/checkout")
public class ApplicationCheckoutController {

    @Autowired
    ApplicationCheckoutMapper checkoutMapper;

    @Autowired
    ApplicationAnnounceMapper announceMapper;

    @Autowired
    WorkerDetailMapper workerDetailMapper;

    @Autowired
    ApplicationAnnounceServiceImpl announceService;

    @Autowired
    StudentDetailMapper studentDetailMapper;

    @PutMapping("/addTask")
    public Result<?> addTask(@RequestBody ApplicationCheckout checkout) {
        try {
            if (checkoutMapper.selNotFinishByApplicantId(checkout.getApplicantId()) != null) {
                return ResultUtils.Err(-1, "退宿不可重复办理！");
            } else {
                checkout.setApplicationId("tssq_" + UUID.fastUUID().toString());
                System.out.println(checkout);
                checkoutMapper.insert(checkout);
                announceMapper.insert(
                        new ApplicationAnnounce(
                                checkout.getApplicationId(),
                                checkout.getApplicantId(),
                                checkout.getDormkeeperId(),
                                false, null, null
                        ));
                return ResultUtils.success("提交退宿申请成功");
            }

        } catch (Exception e) {
            return ResultUtils.Err(-1, e.getMessage());
        }


    }

    @GetMapping("/getCounselor")
    public Result<?> getCounselor() {
        try {
            return ResultUtils.success(workerDetailMapper.selAllCounselorIdName());
        } catch (Exception e) {
            return ResultUtils.Err(-1, e.getMessage());
        }
    }

    @GetMapping("/getDormManager")
    public Result<?> getDormManager() {
        try {
            return ResultUtils.success(workerDetailMapper.selAllDormManagerIdName());
        } catch (Exception e) {
            return ResultUtils.Err(-1, e.getMessage());
        }
    }

    @PostMapping("/updCheckoutCounselor")
    public Result<?> updCheckout(@RequestBody ApplicationCheckout checkout) {
        try {
            announceService.updAnnounce(checkout.getApplicationId(), checkout.getCounselorId());
            return ResultUtils.success(checkoutMapper.updateCounselorById(checkout.getApplicationId(), checkout.getCounselorId()));
        } catch (Exception e) {
            return ResultUtils.Err(-1, e.getMessage());
        }
    }

    @PostMapping("/updDormManager")
    public Result<?> updDormManager(@RequestBody ApplicationCheckout checkout) {
        try {
            announceService.updAnnounce(checkout.getApplicationId(), checkout.getDormmanagerId());
            return ResultUtils.success(checkoutMapper.updateDormManagerById(checkout.getApplicationId(), checkout.getDormmanagerId()));
        } catch (Exception e) {
            return ResultUtils.Err(-1, e.getMessage());
        }
    }

    @GetMapping("/managerApprove")
    public Result<?> managerApprove(@RequestParam String applicationId) {
        //做到这
        try {
            announceMapper.setSuccess(applicationId);
            studentDetailMapper.checkOut(announceMapper.selDetailById(applicationId).getApplicantId());
            return ResultUtils.success(checkoutMapper.setTaskFinish(applicationId));
        } catch (Exception e) {
            return ResultUtils.Err(-1, e.getMessage());
        }
    }
}

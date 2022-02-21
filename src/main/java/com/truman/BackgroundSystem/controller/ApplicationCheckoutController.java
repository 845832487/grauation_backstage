package com.truman.BackgroundSystem.controller;


import cn.hutool.core.lang.UUID;
import com.truman.BackgroundSystem.Common.Result.Result;
import com.truman.BackgroundSystem.Common.Result.ResultUtils;
import com.truman.BackgroundSystem.entity.ApplicationAnnounce;
import com.truman.BackgroundSystem.entity.ApplicationCheckout;
import com.truman.BackgroundSystem.mapper.ApplicationAnnounceMapper;
import com.truman.BackgroundSystem.mapper.ApplicationCheckoutMapper;
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
@RequestMapping("/checkout")
public class ApplicationCheckoutController {

    @Autowired
    ApplicationCheckoutMapper checkoutMapper;

    @Autowired
    ApplicationAnnounceMapper announceMapper;

    @PutMapping("/addTask")
    public Result<?> addTask(@RequestBody ApplicationCheckout checkout) {
        try {
            checkout.setApplicationId("tssq_" + UUID.fastUUID().toString());
            checkoutMapper.insert(checkout);
            announceMapper.insert(
                    new ApplicationAnnounce(
                            checkout.getApplicationId(),
                            checkout.getApplicantId(),
                            checkout.getDormmanagerId(),
                            false, null, null
                    ));
            return ResultUtils.success("提交退宿申请成功");
        } catch (Exception e) {
            return ResultUtils.Err(-1, e.getMessage());
        }


    }
}

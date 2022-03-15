package com.truman.BackgroundSystem.service;

import com.truman.BackgroundSystem.entity.ApplicationTermFinishCheckout;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 冯有恒
 * @since 2022-02-06
 */
public interface IApplicationTermFinishCheckoutService extends IService<ApplicationTermFinishCheckout> {
    ApplicationTermFinishCheckout selNotFinishedTaskListByApplicantId(String id);
}

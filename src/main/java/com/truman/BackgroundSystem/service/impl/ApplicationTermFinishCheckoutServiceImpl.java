package com.truman.BackgroundSystem.service.impl;

import com.truman.BackgroundSystem.entity.ApplicationTermFinishCheckout;
import com.truman.BackgroundSystem.mapper.ApplicationTermFinishCheckoutMapper;
import com.truman.BackgroundSystem.service.IApplicationTermFinishCheckoutService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 冯有恒
 * @since 2022-02-06
 */
@Service
public class ApplicationTermFinishCheckoutServiceImpl extends ServiceImpl<ApplicationTermFinishCheckoutMapper, ApplicationTermFinishCheckout> implements IApplicationTermFinishCheckoutService {
    @Autowired
    ApplicationTermFinishCheckoutMapper applicationTermFinishCheckoutMapper;

    @Override
    public ApplicationTermFinishCheckout selNotFinishedTaskListByApplicantId(String id) {
        return applicationTermFinishCheckoutMapper.getAllNotTermFinishCheckoutByApplicantId(id);
    }
}

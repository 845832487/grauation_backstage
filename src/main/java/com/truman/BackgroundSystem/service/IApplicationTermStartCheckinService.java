package com.truman.BackgroundSystem.service;

import com.truman.BackgroundSystem.entity.ApplicationTermStartCheckin;
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
public interface IApplicationTermStartCheckinService extends IService<ApplicationTermStartCheckin> {
    List<ApplicationTermStartCheckin> selNotFinishedTaskListByApplicantId(String id);
}

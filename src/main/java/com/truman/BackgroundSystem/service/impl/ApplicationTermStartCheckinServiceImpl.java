package com.truman.BackgroundSystem.service.impl;

import com.truman.BackgroundSystem.entity.ApplicationTermStartCheckin;
import com.truman.BackgroundSystem.mapper.ApplicationTermStartCheckinMapper;
import com.truman.BackgroundSystem.service.IApplicationTermStartCheckinService;
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
public class ApplicationTermStartCheckinServiceImpl extends ServiceImpl<ApplicationTermStartCheckinMapper, ApplicationTermStartCheckin> implements IApplicationTermStartCheckinService {

    @Autowired
    ApplicationTermStartCheckinMapper applicationTermStartCheckinMapper;

    @Override
    public List<ApplicationTermStartCheckin> selNotFinishedTaskListByApplicantId(String id) {
        return applicationTermStartCheckinMapper.getAllNotFinishedTermStartCheckinByApplicantId(id);
    }
}

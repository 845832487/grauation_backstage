package com.truman.BackgroundSystem.service.impl;

import com.truman.BackgroundSystem.entity.ApplicationLateReturn;
import com.truman.BackgroundSystem.entity.WorkerDetail;
import com.truman.BackgroundSystem.mapper.ApplicationLateReturnMapper;
import com.truman.BackgroundSystem.mapper.WorkerDetailMapper;
import com.truman.BackgroundSystem.service.IApplicationLateReturnService;
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
public class ApplicationLateReturnServiceImpl extends ServiceImpl<ApplicationLateReturnMapper, ApplicationLateReturn> implements IApplicationLateReturnService {

    @Autowired
    WorkerDetailMapper workerDetailMapper;

    @Autowired
    ApplicationLateReturnMapper applicationLateReturnMapper;

    @Override
    public List<WorkerDetail> getDormKeeperNameId() {
        return workerDetailMapper.selAllDormKeeperIdName();
    }

    @Override
    public List<ApplicationLateReturn> selNotFinishedTaskListByApprovalId(String id) {
        return applicationLateReturnMapper.getAllNotFinishedLateReturnByApprovalId(id);
    }
}

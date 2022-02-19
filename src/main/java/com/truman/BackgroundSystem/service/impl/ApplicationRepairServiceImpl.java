package com.truman.BackgroundSystem.service.impl;

import com.truman.BackgroundSystem.entity.ApplicationRepair;
import com.truman.BackgroundSystem.entity.WorkerDetail;
import com.truman.BackgroundSystem.mapper.ApplicationRepairMapper;
import com.truman.BackgroundSystem.mapper.WorkerDetailMapper;
import com.truman.BackgroundSystem.service.IApplicationRepairService;
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
public class ApplicationRepairServiceImpl extends ServiceImpl<ApplicationRepairMapper, ApplicationRepair> implements IApplicationRepairService {
    @Autowired
    ApplicationRepairMapper repairMapper;

    @Autowired
    WorkerDetailMapper workerDetailMapper;


    @Override
    public List<ApplicationRepair> selNotFinishedTaskListByApplicantID(String id) {
        return repairMapper.getAllNotFinishedRepairByApplicantId(id);
    }

    @Override
    public List<WorkerDetail> getRepairerNameId() {
        return workerDetailMapper.selAllRepairerIdName();
    }
}

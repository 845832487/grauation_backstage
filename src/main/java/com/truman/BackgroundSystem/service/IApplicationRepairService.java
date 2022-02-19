package com.truman.BackgroundSystem.service;

import com.truman.BackgroundSystem.entity.ApplicationRepair;
import com.baomidou.mybatisplus.extension.service.IService;
import com.truman.BackgroundSystem.entity.WorkerDetail;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 冯有恒
 * @since 2022-02-06
 */
public interface IApplicationRepairService extends IService<ApplicationRepair> {

    List<ApplicationRepair> selNotFinishedTaskListByApplicantID(String id);

    List<WorkerDetail> getRepairerNameId();
}

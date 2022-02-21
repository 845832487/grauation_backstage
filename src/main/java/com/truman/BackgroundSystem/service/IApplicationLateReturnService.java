package com.truman.BackgroundSystem.service;

import com.truman.BackgroundSystem.entity.ApplicationLateReturn;
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
public interface IApplicationLateReturnService extends IService<ApplicationLateReturn> {

    List<WorkerDetail> getDormKeeperNameId();

    List<WorkerDetail> getDormKeeperNameIdByStudentId(String id);

    List<ApplicationLateReturn> selNotFinishedTaskListByApprovalId(String id);


}

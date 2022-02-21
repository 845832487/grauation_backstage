package com.truman.BackgroundSystem.service;

import com.truman.BackgroundSystem.entity.ApplicationAnnounce;
import com.baomidou.mybatisplus.extension.service.IService;
import com.truman.BackgroundSystem.entity.ApprovedTask;
import com.truman.BackgroundSystem.entity.NotApprovedTask;
import com.truman.BackgroundSystem.entity.SubmittedTask;

import java.util.ArrayList;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 冯有恒
 * @since 2022-02-06
 */

public interface IApplicationAnnounceService extends IService<ApplicationAnnounce> {

    Integer selNotFinishTaskNum(String id);


    ArrayList<SubmittedTask> selSubmittedTaskList(String id);


    ArrayList<ApprovedTask> selApprovedTaskList(String id);

    ArrayList<NotApprovedTask> selNotApprovedTaskList(String id);
}

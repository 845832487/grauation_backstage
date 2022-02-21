package com.truman.BackgroundSystem.service.impl;

import com.truman.BackgroundSystem.entity.*;
import com.truman.BackgroundSystem.mapper.ApplicationAnnounceMapper;
import com.truman.BackgroundSystem.mapper.ApplicationCheckoutMapper;
import com.truman.BackgroundSystem.mapper.StudentDetailMapper;
import com.truman.BackgroundSystem.mapper.WorkerDetailMapper;
import com.truman.BackgroundSystem.service.IApplicationAnnounceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class ApplicationAnnounceServiceImpl extends ServiceImpl<ApplicationAnnounceMapper, ApplicationAnnounce> implements IApplicationAnnounceService {

    @Autowired
    ApplicationAnnounceMapper announceMapper;

    @Autowired
    ApplicationCheckoutMapper checkoutMapper;

    @Autowired
    StudentDetailMapper studentDetailMapper;

    @Autowired
    WorkerDetailMapper workerDetailMapper;


    @Override
    public Integer selNotFinishTaskNum(String id) {

        return announceMapper.selNumsOfNotFinishAppByAnnounceId(id);
    }

    @Override
    public ArrayList<SubmittedTask> selSubmittedTaskList(String id) {
        List<ApplicationAnnounce> announces = announceMapper.selNotFinishTaskByApplicantId(id);
        ArrayList<SubmittedTask> tasks = new ArrayList<>();
        announces.forEach(announce -> {
            switch (announce.getApplicationId().substring(0, 4)) {
                case "wgsq":
                    //晚归申请
                    tasks.add (new SubmittedTask(
                            announce.getApplicationId(),
                            "晚归申请",
                            "等待宿管审批",
                            announce.getCreateDate()));
                    break;
                //水电维修
                case "sdwx":
                    tasks.add(new SubmittedTask(
                            announce.getApplicationId(),
                            "水电维修",
                            "等待修理工处理",
                            announce.getCreateDate()
                    ));
                    break;
                //开学入住
                case "kxrz":
                    tasks.add(new SubmittedTask(
                            announce.getApplicationId(),
                            "开学入住",
                            "等待宿管审批",
                            announce.getCreateDate()
                    ));
                    break;
                //期末离宿
                case "qmls":
                    tasks.add(new SubmittedTask(
                            announce.getApplicationId(),
                            "期末离宿",
                            "等待宿管审批",
                            announce.getCreateDate()
                    ));
                    break;
                //退宿
                case "tssq":
                    System.out.println(announce);
                    SubmittedTask task = new SubmittedTask(announce.getApplicationId(), "退宿申请", null, announce.getCreateDate());
                    ApplicationCheckout checkout = checkoutMapper.getCheckoutById(announce.getApplicationId());
                    System.out.println(checkout);
                    if (checkout.getCounselorId() == null) {
                        task.setCondition("等待宿管审批");
                    } else if (checkout.getDormmanagerId() == null) {
                        task.setCondition("等待辅导员审批");
                    } else if (checkout.getDormmanagerId() != null) {
                        task.setCondition("等待学生公寓领导审批");
                    }
                    tasks.add(task);
                    break;
            }
        });
        return tasks;
    }


    @Override
    public ArrayList<ApprovedTask> selApprovedTaskList(String id) {
        List<ApplicationAnnounce> announces = announceMapper.selApprovedTaskList(id);
        ArrayList<ApprovedTask> tasks = new ArrayList<>();
        announces.forEach(announce -> {
            switch (announce.getApplicationId().substring(0, 4)){
                case "wgsq":
                    //晚归申请
                    tasks.add(new ApprovedTask(
                            announce.getApplicationId(),
                            "晚归申请",
                            announce.getApplicantId(),
                            announce.getAnnounceId(),
                            announce.getCreateDate()));
                    break;
                //水电维修
                case "sdwx":
                    tasks.add(new ApprovedTask(
                            announce.getApplicationId(),
                            "水电维修",
                            announce.getApplicantId(),
                            announce.getAnnounceId(),

                            announce.getCreateDate()
                    ));
                    break;
                //开学入住
                case "kxrz":
                    tasks.add(new ApprovedTask(
                            announce.getApplicationId(),
                            "开学入住",
                            announce.getApplicantId(),
                            announce.getAnnounceId(),

                            announce.getCreateDate()
                    ));
                    break;
                //期末离宿
                case "qmls":
                    tasks.add(new ApprovedTask(
                            announce.getApplicationId(),
                            "期末离宿",
                            announce.getApplicantId(),
                            announce.getAnnounceId(),

                            announce.getCreateDate()
                    ));
                    break;
                //退宿
                case "tssq":
                    tasks.add(new ApprovedTask(
                            announce.getApplicationId(),
                            "退宿申请",
                            announce.getApplicantId(),
                            announce.getAnnounceId(),

                            announce.getCreateDate()
                    ));
                    break;
            }
        });
        return tasks;
    }


    @Override
    public ArrayList<NotApprovedTask> selNotApprovedTaskList(String id) {
        List<ApplicationAnnounce> announces = announceMapper.selNotFinishTaskByAnnounceId(id);
        ArrayList<NotApprovedTask> tasks = new ArrayList<>();
        announces.forEach(announce -> {
            switch (announce.getApplicationId().substring(0, 4)) {
                case "wgsq":
                    //晚归申请
                    tasks.add(new NotApprovedTask(
                            announce.getApplicationId(),
                            "晚归申请",
                            announce.getApplicantId(),
                            studentDetailMapper.selectById(announce.getApplicantId()).getName(),
                            announce.getCreateDate()));
                    break;
                //水电维修
                case "sdwx":
                    tasks.add(new NotApprovedTask(
                            announce.getApplicationId(),
                            "水电维修",
                            announce.getApplicantId(),
                            studentDetailMapper.selectById(announce.getApplicantId()) == null ?
                                    workerDetailMapper.selectById(announce.getApplicantId()).getName() :
                                    studentDetailMapper.selectById(announce.getApplicantId()).getName(),
                            announce.getCreateDate()));
                    break;
                //开学入住
                case "kxrz":
                    tasks.add(new NotApprovedTask(
                            announce.getApplicationId(),
                            "开学入住",
                            announce.getApplicantId(),
                            studentDetailMapper.selectById(announce.getApplicantId()).getName(),
                            announce.getCreateDate()));
                    break;
                //期末离宿
                case "qmls":
                    tasks.add(new NotApprovedTask(
                            announce.getApplicationId(),
                            "期末离宿",
                            announce.getApplicantId(),
                            studentDetailMapper.selectById(announce.getApplicantId()).getName(),
                            announce.getCreateDate()));
                    break;
                //退宿
                case "tssq":
                    tasks.add(new NotApprovedTask(
                            announce.getApplicationId(),
                            "退宿申请",
                            announce.getApplicantId(),
                            studentDetailMapper.selectById(announce.getApplicantId()).getName(),
                            announce.getCreateDate()));
                    break;
            }
        });
        return tasks;
    }

}

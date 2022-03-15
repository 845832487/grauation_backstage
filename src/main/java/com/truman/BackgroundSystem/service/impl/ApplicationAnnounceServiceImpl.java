package com.truman.BackgroundSystem.service.impl;

import com.truman.BackgroundSystem.entity.*;
import com.truman.BackgroundSystem.mapper.*;
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
    StudentDetailMapper studentDetailMapper;

    @Autowired
    WorkerDetailMapper workerDetailMapper;

    @Autowired
    ApplicationLateReturnMapper lateReturnMapper;

    @Autowired
    ApplicationCheckoutMapper checkoutMapper;

    @Autowired
    ApplicationRepairMapper repairMapper;

    @Autowired
    ApplicationTermStartCheckinMapper termStartCheckinMapper;

    @Autowired
    ApplicationTermFinishCheckoutMapper termFinishCheckoutMapper;


    @Override
    public Integer selNotFinishTaskNum(String id) {

        return announceMapper.selNumsOfNotFinishAppByAnnounceId(id);
    }

    @Override
    public ArrayList<SubmittedTask> selSubmittedTaskList(String id) {
        List<ApplicationAnnounce> announces = announceMapper.selNotFinishTaskByApplicantId(id);
        System.out.println(announces);
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
                    ApplicationCheckout checkout = checkoutMapper.selDetailById(announce.getApplicationId());
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
                            announce.getCreateDate(), announce.getIsFinish()));
                    break;
                //水电维修
                case "sdwx":
                    tasks.add(new ApprovedTask(
                            announce.getApplicationId(),
                            "水电维修",
                            announce.getApplicantId(),
                            announce.getAnnounceId(),

                            announce.getCreateDate(), announce.getIsFinish()));
                    break;
                //开学入住
                case "kxrz":
                    tasks.add(new ApprovedTask(
                            announce.getApplicationId(),
                            "开学入住",
                            announce.getApplicantId(),
                            announce.getAnnounceId(),

                            announce.getCreateDate(), announce.getIsFinish()));
                    break;
                //期末离宿
                case "qmls":
                    tasks.add(new ApprovedTask(
                            announce.getApplicationId(),
                            "期末离宿",
                            announce.getApplicantId(),
                            announce.getAnnounceId(),

                            announce.getCreateDate(), announce.getIsFinish()));
                    break;
                //退宿
                case "tssq":
                    tasks.add(new ApprovedTask(
                            announce.getApplicationId(),
                            "退宿申请",
                            announce.getApplicantId(),
                            announce.getAnnounceId(),

                            announce.getCreateDate(), announce.getIsFinish()));
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

    @Override
    public Object getSubmitDetail(String id) {
        switch (id.substring(0,4)) {
            case "wgsq":
                ApplicationLateReturn lateReturn = lateReturnMapper.selDetailById(id);
                lateReturn.setApprovalId(workerDetailMapper.selNameById(lateReturn.getApprovalId()));
                lateReturn.setApplicantId(studentDetailMapper.selNameById(lateReturn.getApplicantId()));
                return lateReturn;
            case "sdwx":
                ApplicationRepair repair = repairMapper.selDetailById(id);
                repair.setApprovalId(workerDetailMapper.selNameById(repair.getApprovalId()));
                String studentName = studentDetailMapper.selNameById(repair.getApplicantId());
                if (studentName==null) {
                    repair.setApplicantId(workerDetailMapper.selNameById(repair.getApplicantId()));
                }
                return repair;
            case "kxrz":
                ApplicationTermStartCheckin startCheckin = termStartCheckinMapper.selDetailById(id);
                System.out.println(startCheckin);
                startCheckin.setApprovalId(workerDetailMapper.selNameById(startCheckin.getApprovalId()));
                startCheckin.setApplicantId(studentDetailMapper.selNameById(startCheckin.getApplicantId()));
                return startCheckin;
            case "qmls":
                ApplicationTermFinishCheckout finishCheckout = termFinishCheckoutMapper.selDetailById(id);
                finishCheckout.setApprovalId(workerDetailMapper.selNameById(finishCheckout.getApprovalId()));
                finishCheckout.setApplicantId(studentDetailMapper.selNameById(finishCheckout.getApplicantId()));
                return finishCheckout;
            case "tssq":
                ApplicationCheckout checkout = checkoutMapper.selDetailById(id);
                checkout.setApplicantId(studentDetailMapper.selNameById(checkout.getApplicantId()));
                if (checkout.getDormmanagerId() != null) {
                    checkout.setDormmanagerId(workerDetailMapper.selNameById(checkout.getDormmanagerId()));
                    checkout.setCounselorId(workerDetailMapper.selNameById(checkout.getCounselorId()));
                    checkout.setDormkeeperId(workerDetailMapper.selNameById(checkout.getDormkeeperId()));
                } else if (checkout.getCounselorId() != null) {
                    checkout.setCounselorId(workerDetailMapper.selNameById(checkout.getCounselorId()));
                    checkout.setDormkeeperId(workerDetailMapper.selNameById(checkout.getDormkeeperId()));
                }else{checkout.setDormkeeperId(workerDetailMapper.selNameById(checkout.getDormkeeperId()));}
                return checkout;
        }
        return null;
    }

    public Boolean submit(String applicationId) {
        announceMapper.setSuccess(applicationId);
        switch (applicationId.substring(0,4)) {
            case "wgsq":
                lateReturnMapper.setTaskFinish(applicationId);
                return true;
            case "sdwx":
                repairMapper.setTaskFinish(applicationId);
                return true;
            case "kxrz":
                termStartCheckinMapper.setTaskFinish(applicationId);
                studentDetailMapper.checkIn(announceMapper.selDetailById(applicationId).getApplicantId());
                return true;
            case "qmls":
                termFinishCheckoutMapper.setTaskFinish(applicationId);
                studentDetailMapper.checkOut(announceMapper.selDetailById(applicationId).getApplicantId());
                return true;

        }
        return false;
    }

    public Boolean deny(String applicationId) {
        announceMapper.setFail(applicationId);
        switch (applicationId.substring(0,4)) {
            case "wgsq":
                lateReturnMapper.setTaskFinish(applicationId);
                return true;
            case "sdwx":
                repairMapper.setTaskFinish(applicationId);
                return true;
            case "kxrz":
                termStartCheckinMapper.setTaskFinish(applicationId);
                return true;
            case "qmls":
                termFinishCheckoutMapper.setTaskFinish(applicationId);
                return true;
            case "tssq":
                checkoutMapper.setTaskFinish(applicationId);
                return true;
        }
        return false;
    }

    public Boolean updAnnounce(String applicationId, String announceId) {
        return announceMapper.updAnnounce(applicationId, announceId);
    }
}

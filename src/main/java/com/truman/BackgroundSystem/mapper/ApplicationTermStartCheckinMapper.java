package com.truman.BackgroundSystem.mapper;

import com.truman.BackgroundSystem.entity.ApplicationLateReturn;
import com.truman.BackgroundSystem.entity.ApplicationTermStartCheckin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 冯有恒
 * @since 2022-02-06
 */
@Mapper
public interface ApplicationTermStartCheckinMapper extends BaseMapper<ApplicationTermStartCheckin> {
    List<ApplicationTermStartCheckin> getAllNotFinishedTermStartCheckinByApplicantId(String id);

    List<ApplicationTermStartCheckin> getAllNotFinishedTermStartCheckinByApprovalId(String id);

    ApplicationTermStartCheckin selDetailById(String id);

    Boolean setTaskFinish(String applicationId);
}

package com.truman.BackgroundSystem.mapper;

import com.truman.BackgroundSystem.entity.ApplicationLateReturn;
import com.truman.BackgroundSystem.entity.ApplicationRepair;
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
public interface ApplicationRepairMapper extends BaseMapper<ApplicationRepair> {
    List<ApplicationRepair> getAllNotFinishedRepairByApplicantId(String id);

    List<ApplicationRepair> getAllNotFinishedRepairByApprovalId(String id);

    ApplicationRepair selDetailById(String id);

    Boolean setTaskFinish(String applicationId);
}

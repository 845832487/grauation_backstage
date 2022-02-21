package com.truman.BackgroundSystem.mapper;

import com.truman.BackgroundSystem.entity.ApplicationLateReturn;
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
public interface ApplicationLateReturnMapper extends BaseMapper<ApplicationLateReturn> {


    List<ApplicationLateReturn> getAllNotFinishedLateReturnByApprovalId(String id);

    List<ApplicationLateReturn> getAllNotFinishedLateReturnByApplicantId(String id);
}

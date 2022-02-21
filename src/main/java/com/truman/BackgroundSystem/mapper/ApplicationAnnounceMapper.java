package com.truman.BackgroundSystem.mapper;

import com.truman.BackgroundSystem.entity.ApplicationAnnounce;
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
public interface ApplicationAnnounceMapper extends BaseMapper<ApplicationAnnounce> {

    Integer selNumsOfNotFinishAppByAnnounceId(String id);

    List<ApplicationAnnounce> selNotFinishTaskByAnnounceId(String id);


    List<ApplicationAnnounce> selNotFinishTaskByApplicantId(String id);

    List<ApplicationAnnounce> selApprovedTaskList(String id);

}

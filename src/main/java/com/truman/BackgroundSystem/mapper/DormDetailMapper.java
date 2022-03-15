package com.truman.BackgroundSystem.mapper;

import com.truman.BackgroundSystem.entity.DormDetail;
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
public interface DormDetailMapper extends BaseMapper<DormDetail> {
    List<String> selDormBuilding();

    Integer selRoomsLeftByBuildingId(String buildingId);

    List<DormDetail> selDormDetailByBuildingNum(String buildingNum);

    Boolean updRoomLeftById(String buildingId, String dormId,Integer roomsLeft);
}

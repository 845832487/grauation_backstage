package com.truman.BackgroundSystem.mapper;

import com.truman.BackgroundSystem.entity.ApplicationCheckout;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.truman.BackgroundSystem.entity.ApplicationLateReturn;
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
public interface ApplicationCheckoutMapper extends BaseMapper<ApplicationCheckout> {
    ApplicationCheckout getAllNotFinishedCheckOutByApplicantId(String id);

    ApplicationCheckout getCheckoutById(String id);

}

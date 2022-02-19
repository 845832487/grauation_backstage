package com.truman.BackgroundSystem.mapper;

import com.truman.BackgroundSystem.entity.UserLogin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 冯有恒
 * @since 2022-02-06
 */
@Mapper
public interface UserLoginMapper extends BaseMapper<UserLogin> {
    boolean updPasswordById(String id, String password);
}

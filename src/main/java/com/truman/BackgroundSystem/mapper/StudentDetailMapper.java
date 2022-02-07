package com.truman.BackgroundSystem.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.truman.BackgroundSystem.entity.StudentDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 冯有恒
 * @since 2022-02-06
 */
@Mapper
public interface StudentDetailMapper extends BaseMapper<StudentDetail> {

    //管理员修改学生基础信息
    int updStudentBasicInfo(StudentDetail studentDetail);

}

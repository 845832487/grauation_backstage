package com.truman.BackgroundSystem.service.impl;

import com.truman.BackgroundSystem.entity.StudentDetail;
import com.truman.BackgroundSystem.mapper.StudentDetailMapper;
import com.truman.BackgroundSystem.service.IStudentDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 冯有恒
 * @since 2022-02-06
 */
@Service
public class StudentDetailServiceImpl extends ServiceImpl<StudentDetailMapper, StudentDetail> implements IStudentDetailService {

}

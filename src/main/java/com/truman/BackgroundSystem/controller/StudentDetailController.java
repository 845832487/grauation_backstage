package com.truman.BackgroundSystem.controller;


import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.truman.BackgroundSystem.Common.Result.Result;
import com.truman.BackgroundSystem.Common.Result.ResultUtils;
import com.truman.BackgroundSystem.entity.StudentDetail;
import com.truman.BackgroundSystem.entity.UserLogin;
import com.truman.BackgroundSystem.mapper.StudentDetailMapper;
import com.truman.BackgroundSystem.mapper.UserLoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 冯有恒
 * @since 2022-02-06
 */
@RestController
@RequestMapping("/studentDetail")
public class StudentDetailController {

    @Autowired
    StudentDetailMapper studentDetailMapper;

    @Autowired
    UserLoginMapper userLoginMapper;


    @DeleteMapping("/delMultiStudents")
    public Result<?> delMultiStudents(@RequestBody String studentDetails) {
        try {
            JSONObject details = (JSONObject) JSON.parse(studentDetails);
            JSONArray detailArray = (JSONArray) details.get("studentDetails");
            for (int i = 0; i < detailArray.size(); i++) {
                studentDetailMapper.deleteById(detailArray.getJSONObject(i).getString("id"));
                userLoginMapper.deleteById(detailArray.getJSONObject(i).getString("id"));
            }

            return ResultUtils.success("删除成功");
        } catch (Exception e) {
            return ResultUtils.Err(-1, "系统错误，删除失败");
        }
    }

    @PostMapping("/addOneStudent")
    public Result<?> saveOneStudent(@RequestBody StudentDetail student) {
        try {
            studentDetailMapper.insert(student);
            userLoginMapper.insert(new UserLogin(student.getId(), null, "student"));
        } catch (Exception e) {
            return ResultUtils.Err(-1, "请填写完整信息");
        }
        return ResultUtils.success(student);
    }

    @PutMapping("/addMultiStudents")
    public Result<?> addMultiStudents(@RequestBody List<StudentDetail> studentDetails) {
        try {
            studentDetails.forEach(studentDetail -> {
                studentDetailMapper.insert(studentDetail);
                userLoginMapper.insert(new UserLogin(studentDetail.getId(), null, "student"));
            });
            return ResultUtils.success("批量添加成功");
        } catch (Exception e) {
            studentDetails.forEach(studentDetail -> {
                studentDetailMapper.deleteById(studentDetail.getId());
                userLoginMapper.deleteById(studentDetail.getId());
            });
            return ResultUtils.Err(-1, "包含学号重复，添加失败");
        }
    }


    @PutMapping("/updStudent")
    public Result<?> updStudent(@RequestBody StudentDetail student) {

        try {
            studentDetailMapper.updateById(student);
        } catch (Exception e) {
            return ResultUtils.Err(-1, "修改失败");
        }
        return ResultUtils.success(student);
    }

    @GetMapping("/findPage")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {

        LambdaQueryWrapper<StudentDetail> wrapper = Wrappers.<StudentDetail>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(StudentDetail::getName, search);
        }
        Page<StudentDetail> studentDetailPage = studentDetailMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);


        if (studentDetailPage != null) {
            return ResultUtils.success(studentDetailPage);
        } else return ResultUtils.Err(-1, "查询结果为空");

    }

    @DeleteMapping("/delSingleStudent")
    public Result<?> delSingleStudent(String studentId) {
        try {
            studentDetailMapper.deleteById(studentId);
            userLoginMapper.deleteById(studentId);
        } catch (Exception e) {
            return ResultUtils.Err(-1, "删除失败");
        }
        return ResultUtils.success(studentId);
    }

    @GetMapping("/getStudentDetailById")
    public Result<?> getStudentDetailById(String studentId) {
        try {
            return ResultUtils.success(studentDetailMapper.selectById(studentId));
        } catch (Exception e) {
            return ResultUtils.Err(-1, e.getMessage());
        }
    }

    @PostMapping("/updStudentPersonalInfo")
    public Result<?> updStudentPersonalInfo(@RequestBody StudentDetail studentDetail) {
        try {
            studentDetailMapper.updateById(studentDetail);
            return ResultUtils.success("修改成功");
        } catch (Exception e) {
            return ResultUtils.Err(-1, e.getMessage());
        }
    }
}

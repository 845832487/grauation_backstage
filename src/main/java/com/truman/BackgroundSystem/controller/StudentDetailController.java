package com.truman.BackgroundSystem.controller;


import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.truman.BackgroundSystem.Common.Result.Result;
import com.truman.BackgroundSystem.Common.Result.ResultUtils;
import com.truman.BackgroundSystem.entity.StudentDetail;
import com.truman.BackgroundSystem.mapper.StudentDetailMapper;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 冯有恒
 * @since 2022-02-06
 */
@RestController
@RequestMapping("/StudentDetail")
public class StudentDetailController {

    @Autowired
    StudentDetailMapper studentDetailMapper;

    @PostMapping("/addOneStudent")
    public Result<?> saveOneStudent(@RequestBody StudentDetail student) {
        try {
            studentDetailMapper.insert(student);
        } catch (Exception e) {
            return ResultUtils.Err(-1, "插入失败");
        }
        return ResultUtils.success(student);
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
        System.out.println(studentId);
        try {
            Integer id = Integer.parseInt(studentId);
            studentDetailMapper.deleteById(id);
        } catch (Exception e) {
            return ResultUtils.Err(-1, "删除失败");
        }
        return ResultUtils.success(studentId);
    }
}

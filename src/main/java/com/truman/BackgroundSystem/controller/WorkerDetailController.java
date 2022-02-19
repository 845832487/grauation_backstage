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
import com.truman.BackgroundSystem.entity.WorkerDetail;
import com.truman.BackgroundSystem.mapper.StudentDetailMapper;
import com.truman.BackgroundSystem.mapper.UserLoginMapper;
import com.truman.BackgroundSystem.mapper.WorkerDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 冯有恒
 * @since 2022-02-06
 */
@RestController
@RequestMapping("/workerDetail")
public class WorkerDetailController {

    @Autowired
    WorkerDetailMapper workerDetailMapper;

    @Autowired
    UserLoginMapper userLoginMapper;


    @DeleteMapping("/delMultiWorkers")
    public Result<?> delMultiWorkers(@RequestBody String workerDetails) {
        try {
            JSONObject details = (JSONObject) JSON.parse(workerDetails);
            JSONArray detailArray = (JSONArray) details.get("workerDetails");
            for (int i = 0; i < detailArray.size(); i++) {
                workerDetailMapper.deleteById(detailArray.getJSONObject(i).getString("id"));
                userLoginMapper.deleteById(detailArray.getJSONObject(i).getString("id"));
            }

            return ResultUtils.success("删除成功");
        } catch (Exception e) {
            return ResultUtils.Err(-1, "系统错误，删除失败");
        }
    }

    @PostMapping("/addOneWorker")
    public Result<?> saveOneStudent(@RequestBody WorkerDetail worker) {
        try {
            workerDetailMapper.insert(worker);
            userLoginMapper.insert(new UserLogin(worker.getId(), null, "worker"));
        } catch (Exception e) {
            return ResultUtils.Err(-1, "请填写完整信息");
        }
        return ResultUtils.success(worker);
    }

    @PutMapping("/addMultiWorkers")
    public Result<?> addMultiStudents(@RequestBody List<WorkerDetail> workerDetails) {
        try {
            workerDetails.forEach(workerDetail -> {
                workerDetailMapper.insert(workerDetail);
                userLoginMapper.insert(new UserLogin(workerDetail.getId(), null, "worker"));
            });
            return ResultUtils.success("批量添加成功");
        } catch (Exception e) {
            workerDetails.forEach(workerDetail -> {
                workerDetailMapper.deleteById(workerDetail.getId());
                userLoginMapper.deleteById(workerDetail.getId());
            });
            return ResultUtils.Err(-1, "包含重复账号，添加失败");
        }
    }


    @PutMapping("/updWorker")
    public Result<?> updStudent(@RequestBody WorkerDetail workerDetail) {

        try {
            workerDetailMapper.updateById(workerDetail);
        } catch (Exception e) {
            return ResultUtils.Err(-1, "修改失败");
        }
        return ResultUtils.success(workerDetail);
    }

    @GetMapping("/findPage")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {

        LambdaQueryWrapper<WorkerDetail> wrapper = Wrappers.<WorkerDetail>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(WorkerDetail::getName, search);
        }
        Page<WorkerDetail> workerDetailPage = workerDetailMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);


        if (workerDetailPage != null) {
            return ResultUtils.success(workerDetailPage);
        } else return ResultUtils.Err(-1, "查询结果为空");

    }

    @DeleteMapping("/delSingleWorker")
    public Result<?> delSingleStudent(String workerId) {
        try {
            workerDetailMapper.deleteById(workerId);
            userLoginMapper.deleteById(workerId);
        } catch (Exception e) {
            return ResultUtils.Err(-1, "删除失败");
        }
        return ResultUtils.success(workerId);
    }

    @GetMapping("/getWorkerDetailById")
    public Result<?> getWorkerDetailById(String workerId) {
        try {
            return ResultUtils.success(workerDetailMapper.selectById(workerId));
        } catch (Exception e) {
            return ResultUtils.Err(-1, e.getMessage());
        }
    }

    @PostMapping("/updWorkerPersonalInfo")
    public Result<?> updWorkerPersonalInfo(@RequestBody WorkerDetail workerDetail) {
        try {
            workerDetailMapper.updateById(workerDetail);
            return ResultUtils.success("修改成功");
        } catch (Exception e) {
            return ResultUtils.Err(-1, e.getMessage());
        }
    }
}








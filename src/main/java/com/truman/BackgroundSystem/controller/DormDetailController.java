package com.truman.BackgroundSystem.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.truman.BackgroundSystem.Common.Result.Result;
import com.truman.BackgroundSystem.Common.Result.ResultUtils;
import com.truman.BackgroundSystem.entity.DormDetail;
import com.truman.BackgroundSystem.entity.StudentDetail;
import com.truman.BackgroundSystem.mapper.DormDetailMapper;
import com.truman.BackgroundSystem.mapper.StudentDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
@RequestMapping("/dormDetail")
public class DormDetailController {

    @Autowired
    DormDetailMapper dormDetailMapper;

    @Autowired
    StudentDetailMapper studentDetailMapper;


    @GetMapping("/findPage")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {

        LambdaQueryWrapper<DormDetail> wrapper = Wrappers.<DormDetail>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(DormDetail::getBuildingId, search).or().like(DormDetail::getDormId, search);
        }
        Page<DormDetail> dormDetailPage = dormDetailMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);


        if (dormDetailPage != null) {
            return ResultUtils.success(dormDetailPage);
        } else return ResultUtils.Err(-1, "查询结果为空");
    }

    @PutMapping("/addMultiDorms")
    public Result<?> addMultiDorms(@RequestBody List<DormDetail> dormDetails) {
        try {
            dormDetails.forEach(dormDetail -> {
                dormDetailMapper.insert(dormDetail);
            });
            return ResultUtils.success("批量添加成功");
        } catch (Exception e) {
            return ResultUtils.Err(-1, e.getMessage());
        }
    }

    @GetMapping("/findStudent")
    public Result<?> findStudent(@RequestParam String buildingNum, @RequestParam String dormNum) {
        try {
            return ResultUtils.success(studentDetailMapper.selByDorm(buildingNum, dormNum));
        } catch (Exception e) {
            return ResultUtils.Err(-1, e.getMessage());
        }
    }

    @GetMapping("/getDormBuilding")
    public Result<?> getDormBuilding() {
        try {
            return ResultUtils.success(dormDetailMapper.selDormBuilding());
        } catch (Exception e) {
            return ResultUtils.Err(-1, e.getMessage());
        }
    }

    @GetMapping("/getRoomsLeft")
    public Result<?> getRoomsLeft(@RequestParam String buildingId) {
        try {
            return ResultUtils.success(dormDetailMapper.selRoomsLeftByBuildingId(buildingId));
        } catch (Exception e) {
            return ResultUtils.Err(-1, e.getMessage());
        }
    }

    @GetMapping("/getStudentsLeft")
    public Result<?> getStudentsLeft(@RequestParam Boolean sex) {
        try {
            return ResultUtils.success(studentDetailMapper.selStudentsLeftBySex(sex));
        } catch (Exception e) {
            return ResultUtils.Err(-1, e.getMessage());
        }
    }

    @GetMapping("/allocate")
    public Result<?> allocate(@RequestParam String buildingNum,@RequestParam Boolean sex) {
        try {
            List<DormDetail> dorms = dormDetailMapper.selDormDetailByBuildingNum(buildingNum);
            List<StudentDetail> students = studentDetailMapper.selStudentLeftDetailBySex(sex);
            ArrayList<StudentDetail> changedStudents = new ArrayList<>();
            int dormIdx = 0;
            int dormMax = dorms.size();
            for (StudentDetail student : students) {
                if (dorms.get(dormIdx).getRoomsLeft() > 0) {
                    student.setBuildingNum(buildingNum);
                    student.setDormNum(dorms.get(dormIdx).getDormId());
                    changedStudents.add(student);
                    dorms.get(dormIdx).setRoomsLeft(dorms.get(dormIdx).getRoomsLeft()-1);
                    if (dorms.get(dormIdx).getRoomsLeft() == 0) {
                        dormIdx++;
                        if (dormIdx == dormMax) {
                            break;
                        }
                    }
                }
            }

            for (StudentDetail changedStudent : changedStudents) {
                studentDetailMapper.updateById(changedStudent);
            }
            for (int i = 0; i < dormIdx+1; i++) {
                dormDetailMapper.updRoomLeftById(
                        dorms.get(i).getBuildingId(),
                        dorms.get(i).getDormId(),
                        dorms.get(i).getRoomsLeft());
            }

            return ResultUtils.success("分配成功");
        } catch (Exception e) {
            return ResultUtils.Err(-1, e.getMessage());
        }
    }

}

package com.truman.BackgroundSystem.controller;


import com.truman.BackgroundSystem.Common.Result.Result;
import com.truman.BackgroundSystem.Common.Result.ResultUtils;
import com.truman.BackgroundSystem.entity.*;
import com.truman.BackgroundSystem.mapper.StudentDetailMapper;
import com.truman.BackgroundSystem.mapper.UserLoginMapper;
import com.truman.BackgroundSystem.mapper.WorkerDetailMapper;
import com.truman.BackgroundSystem.service.impl.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.mail.MessagingException;
import java.util.Random;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 冯有恒
 * @since 2022-02-06
 */
@RestController
@RequestMapping("/userRole")
public class UserRoleController {


    @Autowired
    UserLoginMapper userLoginMapper;


    /*
    * 返回用户权限信息
    * */
    @GetMapping("/getInfo")
    public Result<?> getUserInfo(@RequestParam String id) {
        try {
            UserLogin userLogin = userLoginMapper.selectById(id);
            if (userLogin != null) {
                return ResultUtils.success(userLogin);
            } else return ResultUtils.Err(-1, "未找到用户权限信息");
        } catch (Exception e) {
            return ResultUtils.Err(-1, e.getMessage());
        }
    }


}

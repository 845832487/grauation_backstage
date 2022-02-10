package com.truman.BackgroundSystem.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.truman.BackgroundSystem.Common.Result.Result;
import com.truman.BackgroundSystem.Common.Result.ResultUtils;
import com.truman.BackgroundSystem.entity.*;
import com.truman.BackgroundSystem.mapper.StudentDetailMapper;
import com.truman.BackgroundSystem.mapper.UserLoginMapper;
import com.truman.BackgroundSystem.mapper.UserRoleMapper;
import com.truman.BackgroundSystem.mapper.WorkerDetailMapper;
import com.truman.BackgroundSystem.service.impl.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 冯有恒
 * @since 2022-02-06
 */
@RestController
@RequestMapping("/loginPage")
public class UserLoginController {

    @Autowired
    UserLoginMapper userLoginMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    StudentDetailMapper studentDetailMapper;

    @Autowired
    WorkerDetailMapper workerDetailMapper;

    @Autowired
    EmailServiceImpl emailService;

    @Autowired
    @Qualifier("redisTemplate")
    RedisTemplate redistemplate;


    /*
     * 登录验证
     * */
    @PostMapping("/login")
    public Result<?> userLogin(@RequestBody UserLogin user) {
        System.out.println(user);
        try {
            UserLogin res = userLoginMapper.selectOne(Wrappers.<UserLogin>lambdaQuery()
                    .eq(UserLogin::getId, user.getId())
                    .eq(UserLogin::getPassword, user.getPassword()));
            if (res != null) {
                return ResultUtils.success(res);
            } else return ResultUtils.Err(-1, "账号或密码错误");
        } catch (Exception e) {
            return ResultUtils.Err(-1, "系统错误");
        }
    }

    /*
     * 找回密码发送邮件
     * */
    @GetMapping("/getForgetPasswordCode")
    public Result<?> getForgetPasswordCode(@RequestParam String id) {
        try {
            UserRole userRole = userRoleMapper.selectById(id);
            String emailTo;
            int code = new Random().nextInt(9000) + 1000;
            String content = "您找回密码的验证码为[" + code + "]密码将于10分钟后过期";
            String title = "密码找回验证码";
            if (userRole == null) {
                return ResultUtils.Err(-1, "学号不存在");
            } else if (userRole.getRole().equals("student")) {
                StudentDetail student = studentDetailMapper.selectById(id);
                emailTo = student.getEmail();
            } else {
                WorkerDetail worker = workerDetailMapper.selectById(id);
                emailTo = worker.getEmail();
            }
            emailService.sendEmail(new Email(title, content, emailTo));
            redistemplate.opsForValue().set(id + "_forgetPassword", code, 600, TimeUnit.SECONDS);
            StringBuilder emailAddr = new StringBuilder(emailTo);
            for (int i = 1; i < emailTo.length(); i++) {
                if (emailTo.charAt(i) == '@') {
                    break;
                } else {
                    emailAddr.setCharAt(i, '*');
                }
            }
            return ResultUtils.success("邮件发送成功请登录绑定邮箱（" + emailAddr + "）,请注意查收");
        } catch (MessagingException e) {
            return ResultUtils.Err(-1, "发送邮件失败");
        }
    }


    /*
     * 检查邮箱验证码是否正确
     * */
    @PostMapping("/checkForgetPasswordEmail")
    public Result<?> checkForgetPasswordEmail(@RequestBody ForgetPassword forgetPasswordCheck) {
        System.out.println(forgetPasswordCheck);
        try {
            String code = Objects.requireNonNull(redistemplate.opsForValue().get(forgetPasswordCheck.getId() + "_forgetPassword")).toString();
            if (forgetPasswordCheck.getCode().equals(code)) {
                return ResultUtils.success("验证成功");
            } else return ResultUtils.Err(-1, "验证码错误");
        } catch (Exception e) {
            return ResultUtils.Err(-1, "验证码过期");
        }

    }

    @PostMapping("/changePassword")
    public Result<?> changePassword(@RequestBody UserLogin userLogin) {
        try {
            userLoginMapper.updateById(userLogin);
            return ResultUtils.success("密码修改成功");
        } catch (Exception e) {
            return ResultUtils.Err(-1, "系统错误，修改失败");
        }

    }
}

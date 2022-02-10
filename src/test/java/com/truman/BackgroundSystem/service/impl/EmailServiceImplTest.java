package com.truman.BackgroundSystem.service.impl;

import com.truman.BackgroundSystem.entity.Email;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.mail.MessagingException;

@SpringBootTest
class EmailServiceImplTest {

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    @Qualifier("redisTemplate")
    RedisTemplate redisTemplate;
    @Test
    public void send() throws MessagingException {
        Email email = new Email();

        email.setContent("刘佳璐你好我是冯有恒");
        email.setTo("845832487@qq.com");
        email.setTitle("刘佳璐是我女朋友");
        emailService.sendEmail(email);

    }

    @Test
    public void test() {
        redisTemplate.opsForValue().set("a", "this is a");

        System.out.println(redisTemplate.opsForValue().get("a"));
    }
}
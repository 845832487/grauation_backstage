package com.truman.BackgroundSystem.service.impl;

import com.truman.BackgroundSystem.entity.Email;
import com.truman.BackgroundSystem.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    /*gai */
    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;


    @Override
    public void sendEmail(Email email) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message);
        mimeMessageHelper.setSubject(email.getTitle());
        mimeMessageHelper.setText(email.getContent());
        mimeMessageHelper.setTo(email.getTo());
        mimeMessageHelper.setFrom(from);
        javaMailSender.send(message);

    }
}

package com.truman.BackgroundSystem.service;

import com.truman.BackgroundSystem.entity.Email;

import javax.mail.MessagingException;

public interface EmailService {
    public void sendEmail(Email email) throws MessagingException;
}

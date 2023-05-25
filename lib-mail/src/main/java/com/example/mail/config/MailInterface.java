package com.example.mail.config;

import org.springframework.mail.javamail.JavaMailSender;

public interface MailInterface {

    JavaMailSender initialize();


}
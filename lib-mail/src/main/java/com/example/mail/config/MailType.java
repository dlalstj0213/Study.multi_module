package com.example.mail.config;

public enum MailType {
    STRING("stringTemplateResolver"),
    HTML("htmlTemplateResolver"),
    ;

    private final String beanName;

    MailType(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return this.beanName;
    }
}
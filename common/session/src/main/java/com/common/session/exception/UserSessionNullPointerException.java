package com.common.session.exception;

public class UserSessionNullPointerException extends CommonSessionException {

    public UserSessionNullPointerException() {
        super("User Session is not exist");
    }
}
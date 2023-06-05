package com.common.session.exception;

public class SessionNullPointerException extends CommonSessionException {
    public SessionNullPointerException() {
        super("Session is not exist");
    }
}
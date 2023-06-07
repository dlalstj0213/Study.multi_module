package com.common.session;

import com.common.session.exception.CommonSessionException;
import com.common.session.model.UserSession;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public interface CommonSessionTemplate {

    Optional<HttpSession> getSession();

    HttpSession createSession();

    void removeSession();

    void removeSession(String sessionId);

    boolean hasSession(String sessionId);

    UserSession createSessionBy(UserSession userSession);

    UserSession getUserSession() throws CommonSessionException;

    void setUserSession(UserSession userSession);

}
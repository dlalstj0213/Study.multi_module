package com.common.session;

import com.common.session.exception.CommonSessionException;
import com.common.session.exception.SessionNullPointerException;
import com.common.session.exception.UserSessionNullPointerException;
import com.common.session.model.UserSession;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

public class CommonSession implements CommonSessionTemplate {

    private static final String USER_SESSION_KEY = "user";

    private final RedisOperations<String, Object> operations;

    CommonSession(RedisOperations<String, Object> operations) {
        this.operations = operations;
    }

    @Override
    public Optional<HttpSession> getSession() {
        return Optional.ofNullable(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()
                                                                                                               .getSession(
                                                                                                                       false));
    }

    @Override
    public HttpSession createSession() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()
                                                                                           .getSession(true);
    }

    @Override
    public void removeSession() {
        Optional<HttpSession> optional = getSession();
        optional.ifPresent(HttpSession::invalidate);
    }

    @Override
    public void removeSession(String sessionId) {
        if (!hasSession(sessionId)) return;
        operations.delete(resolveHashKey(sessionId));
    }

    @Override
    public boolean hasSession(String sessionId) {
        return Boolean.TRUE.equals(operations.hasKey(resolveHashKey(sessionId)));
    }

    @Override
    public UserSession createSessionBy(UserSession userSession) {
        Optional<HttpSession> optional = getSession();
        if (optional.isPresent()) {
            HttpSession oldSession = optional.get();
            oldSession.invalidate();
        }
        HttpSession session = createSession();
        session.setAttribute(USER_SESSION_KEY, userSession);
        return (UserSession) session.getAttribute(USER_SESSION_KEY);
    }

    @Override
    public UserSession getUserSession() throws CommonSessionException {
        HttpSession session = getSession().orElseThrow(SessionNullPointerException::new);
        UserSession userSession = (UserSession) session.getAttribute(USER_SESSION_KEY);
        if (userSession == null) throw new UserSessionNullPointerException();
        else return userSession;
    }

    @Override
    public void setUserSession(UserSession userSession) {
        HttpSession session = getSession().orElse(createSession());
        session.setAttribute(USER_SESSION_KEY, userSession);
    }

    private String resolveHashKey(String id) {
        final String HASH_KEY_NAMESPACE = "spring:session:sessions:";
        return HASH_KEY_NAMESPACE + id;
    }
}
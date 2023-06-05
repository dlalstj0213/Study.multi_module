package com.common.session.model;

import java.io.Serializable;

public record UserSession(String userId, String username, String email) implements Serializable {}
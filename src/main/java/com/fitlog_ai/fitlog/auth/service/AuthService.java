package com.fitlog_ai.fitlog.auth.service;

import com.fitlog_ai.fitlog.auth.dto.RegisterRequest;

public interface AuthService {
    void register(RegisterRequest Request);
}

package com.fitlog_ai.fitlog.auth.dto;

public record LoginRequest(
        String email,
        String password
) {
}
package com.fitlog_ai.fitlog.auth.dto;

import com.fitlog_ai.fitlog.auth.enums.ActivityLevel;
import com.fitlog_ai.fitlog.auth.enums.Goal;

import java.time.LocalDate;

public record RegisterRequest(
        String name,
        String email,
        String password,
        Integer height,
        LocalDate birthDate,
        Goal goal,
        ActivityLevel activityLevel
) {
}
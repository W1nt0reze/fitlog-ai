package com.fitlog_ai.fitlog.training.dto;

import com.fitlog_ai.fitlog.training.enums.Difficulty;

import java.time.LocalDate;

public record WorkoutResponse(
        Long id,
        String title,
        LocalDate date,
        Integer durationMinutes,
        Difficulty difficulty,
        String notes
) {
}

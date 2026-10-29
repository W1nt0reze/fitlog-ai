package com.fitlog_ai.fitlog.training.dto;

import com.fitlog_ai.fitlog.training.enums.Difficulty;

import java.time.LocalDate;
import jakarta.validation.constraints.*;

public record WorkoutRequest(
        @NotBlank @Size(max = 255) String title,
        @NotNull LocalDate date,
        @Positive Integer durationMinutes,
        Difficulty difficulty,
        @Size(max = 1000) String notes
) {
}

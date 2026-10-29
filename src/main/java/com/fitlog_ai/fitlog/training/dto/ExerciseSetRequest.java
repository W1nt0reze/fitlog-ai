package com.fitlog_ai.fitlog.training.dto;

public record ExerciseSetRequest(
        Long exerciseId,
        Integer setNumber,
        Double weight,
        Integer reps,
        Integer rpe,
        Integer restSeconds,
        Boolean completed
) {
}

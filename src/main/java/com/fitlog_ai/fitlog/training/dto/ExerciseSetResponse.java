package com.fitlog_ai.fitlog.training.dto;

public record ExerciseSetResponse(
        Long id,
        Long exerciseId,
        String exerciseName,
        Double weight,
        Integer reps,
        Integer rpe,
        Integer restSeconds,
        Boolean completed
) {
}

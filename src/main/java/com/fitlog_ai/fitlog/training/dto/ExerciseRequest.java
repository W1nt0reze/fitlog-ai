package com.fitlog_ai.fitlog.training.dto;

import com.fitlog_ai.fitlog.training.enums.Equipment;
import com.fitlog_ai.fitlog.training.enums.ExerciseType;
import com.fitlog_ai.fitlog.training.enums.MuscleGroup;

public record ExerciseRequest(
        String name,
        String description,
        MuscleGroup muscleGroup,
        ExerciseType exerciseType,
        Equipment equipment
) {
}

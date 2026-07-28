package com.fitlog_ai.fitlog.training.mapper;

import org.springframework.stereotype.Component;
import com.fitlog_ai.fitlog.training.dto.ExerciseRequest;
import com.fitlog_ai.fitlog.training.dto.ExerciseResponse;
import com.fitlog_ai.fitlog.training.entity.Exercise;

@Component
public class ExerciseMapper {
    public Exercise toEntity(ExerciseRequest request) {
        Exercise exercise = new Exercise();
        exercise.setName(request.name());
        exercise.setDescription(request.description());
        exercise.setMuscleGroup(request.muscleGroup());
        exercise.setExerciseType(request.exerciseType());
        exercise.setEquipment(request.equipment());
        return exercise;
    }

    public ExerciseResponse toResponse(Exercise exercise) {
        return new ExerciseResponse(
                exercise.getId(),
                exercise.getName(),
                exercise.getDescription(),
                exercise.getMuscleGroup(),
                exercise.getExerciseType(),
                exercise.getEquipment());
    }
}

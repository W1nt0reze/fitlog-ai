package com.fitlog_ai.fitlog.training.mapper;

import com.fitlog_ai.fitlog.training.dto.ExerciseResponse;
import com.fitlog_ai.fitlog.training.dto.ExerciseSetRequest;
import com.fitlog_ai.fitlog.training.dto.ExerciseSetResponse;
import com.fitlog_ai.fitlog.training.entity.ExerciseSet;
import org.springframework.stereotype.Component;

@Component
public class ExerciseSetMapper {
    public ExerciseSet toEntity(ExerciseSetRequest request) {
        ExerciseSet exerciseSet = new ExerciseSet();
        exerciseSet.setSetNumber(request.setNumber());
        exerciseSet.setWeight(request.weight());
        exerciseSet.setReps(request.reps());
        exerciseSet.setRpe(request.rpe());
        exerciseSet.setRestSeconds(request.restSeconds());
        exerciseSet.setCompleted(request.completed());
        return exerciseSet;
    }

    public ExerciseSetResponse toResponse(ExerciseSet exerciseSet) {
        return new ExerciseSetResponse(
                exerciseSet.getId(),
                exerciseSet.getExercise().getId(),
                exerciseSet.getExercise().getName(),
                exerciseSet.getWeight(),
                exerciseSet.getReps(),
                exerciseSet.getRpe(),
                exerciseSet.getRestSeconds(),
                exerciseSet.isCompleted()
        );
    }
}

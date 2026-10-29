package com.fitlog_ai.fitlog.training.service;

import com.fitlog_ai.fitlog.training.dto.ExerciseSetRequest;
import com.fitlog_ai.fitlog.training.dto.ExerciseSetResponse;

import java.util.List;

public interface ExerciseSetService {

    List<ExerciseSetResponse> getAllExerciseSets();

    List<ExerciseSetResponse> getExerciseSetsByWorkoutId(Long workoutId);

    List<ExerciseSetResponse> getExerciseSetsByExerciseId(Long exerciseId);

    ExerciseSetResponse createExerciseSet(Long workoutId, ExerciseSetRequest request);

    void deleteExerciseSet(Long id);

    ExerciseSetResponse updateExerciseSet(Long id, ExerciseSetRequest request);
}

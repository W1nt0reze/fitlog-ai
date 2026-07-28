package com.fitlog_ai.fitlog.training.service;

import com.fitlog_ai.fitlog.training.dto.ExerciseRequest;
import com.fitlog_ai.fitlog.training.dto.ExerciseResponse;

import java.util.List;

public interface ExerciseService {
    List<ExerciseResponse> getAllExercises();

    ExerciseResponse getExerciseById(Long id);

    ExerciseResponse createExercise(ExerciseRequest request);

    void deleteExercise(Long id);

    ExerciseResponse updateExercise(Long id, ExerciseRequest request);
}

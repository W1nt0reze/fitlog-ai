package com.fitlog_ai.fitlog.training.service;

import com.fitlog_ai.fitlog.training.dto.WorkoutRequest;
import com.fitlog_ai.fitlog.training.dto.WorkoutResponse;

import java.util.List;

public interface WorkoutService {
    List<WorkoutResponse> getAllWorkouts();

    WorkoutResponse getWorkoutById(Long id);

    WorkoutResponse createWorkout(Long Id, WorkoutRequest request);

    void deleteWorkout(Long id);

    WorkoutResponse updateWorkout(Long id, WorkoutRequest request);

    List<WorkoutResponse> getByUserId(Long Id);
}

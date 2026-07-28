package com.fitlog_ai.fitlog.training.service;

import com.fitlog_ai.fitlog.auth.entity.User;
import com.fitlog_ai.fitlog.training.dto.WorkoutRequest;
import com.fitlog_ai.fitlog.training.dto.WorkoutResponse;
import com.fitlog_ai.fitlog.training.entity.Workout;

import java.util.List;

public interface WorkoutService {
    List<WorkoutResponse> getAllWorkouts();

    WorkoutResponse getWorkoutById(Long id);

    WorkoutResponse createWorkout(WorkoutRequest request);

    void deleteWorkout(Long id);

    WorkoutResponse updateWorkout(Long id, WorkoutRequest request);

    List<WorkoutResponse> getByUserId(Long userId);
}

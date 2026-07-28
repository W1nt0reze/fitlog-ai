package com.fitlog_ai.fitlog.training.controller;

import com.fitlog_ai.fitlog.training.dto.WorkoutRequest;
import com.fitlog_ai.fitlog.training.dto.WorkoutResponse;
import com.fitlog_ai.fitlog.training.service.WorkoutService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {
    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping
    public List<WorkoutResponse> getWorkouts() {
        return workoutService.getAllWorkouts();
    }

    @GetMapping("/{id}")
    public WorkoutResponse getWorkoutById(@PathVariable Long id) {
        return workoutService.getWorkoutById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorkoutResponse createWorkout(@RequestBody WorkoutRequest request) {
        return workoutService.createWorkout(request);
    }

    @DeleteMapping("/{id}")
    public void deleteWorkout(@PathVariable Long id) {
        workoutService.deleteWorkout(id);
    }

    @PutMapping("/{id}")
    public WorkoutResponse updateWorkout(@PathVariable Long id, @RequestBody WorkoutRequest request) {
        return workoutService.updateWorkout(id, request);
    }

    @GetMapping("/user/{userId}")
    public List<WorkoutResponse> getWorkoutByUserId(@PathVariable Long id) {
        return workoutService.getByUserId(id);
    }
}

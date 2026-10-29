package com.fitlog_ai.fitlog.training.controller;

import com.fitlog_ai.fitlog.training.dto.ExerciseSetRequest;
import com.fitlog_ai.fitlog.training.dto.ExerciseSetResponse;
import com.fitlog_ai.fitlog.training.service.ExerciseSetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ExerciseSetController {

    private final ExerciseSetService exerciseSetService;

    public ExerciseSetController(ExerciseSetService exerciseSetService) {
        this.exerciseSetService = exerciseSetService;
    }

    @GetMapping("/workouts/{workoutId}/sets")
    public List<ExerciseSetResponse> getWorkoutSets(@PathVariable Long workoutId) {
        return exerciseSetService.getExerciseSetsByWorkoutId(workoutId);
    }

    @PostMapping("/workouts/{workoutId}/sets")
    public ExerciseSetResponse createExerciseSet(
            @PathVariable Long workoutId,
            @RequestBody ExerciseSetRequest request) {

        return exerciseSetService.createExerciseSet(workoutId, request);
    }

    @PutMapping("/exercise-sets/{id}")
    public ExerciseSetResponse updateExerciseSet(
            @PathVariable Long id,
            @RequestBody ExerciseSetRequest request) {

        return exerciseSetService.updateExerciseSet(id, request);
    }

    @DeleteMapping("/exercise-sets/{id}")
    public void deleteExerciseSet(@PathVariable Long id) {
        exerciseSetService.deleteExerciseSet(id);
    }
}

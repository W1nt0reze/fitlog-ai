package com.fitlog_ai.fitlog.training.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.fitlog_ai.fitlog.training.dto.ExerciseRequest;
import com.fitlog_ai.fitlog.training.dto.ExerciseResponse;
import com.fitlog_ai.fitlog.training.service.ExerciseService;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {
    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping
    public List<ExerciseResponse> getAllExercises() {
        return exerciseService.getAllExercises();
    }

    @GetMapping("/{id}")
    public ExerciseResponse getExerciseById(@PathVariable Long id) {
        return exerciseService.getExerciseById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExerciseResponse createExercise(@RequestBody ExerciseRequest request) {
        return exerciseService.createExercise(request);
    }

    @DeleteMapping("/{id}")
    public void deleteExercise(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
    }

    @PutMapping("/{id}")
    public ExerciseResponse updateExercise(@PathVariable Long id, @RequestBody ExerciseRequest request) {
        return exerciseService.updateExercise(id, request);
    }

}

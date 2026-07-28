package com.fitlog_ai.fitlog.training.service;

import com.fitlog_ai.fitlog.common.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.fitlog_ai.fitlog.training.dto.ExerciseRequest;
import com.fitlog_ai.fitlog.training.dto.ExerciseResponse;
import com.fitlog_ai.fitlog.training.entity.Exercise;
import com.fitlog_ai.fitlog.training.mapper.ExerciseMapper;
import com.fitlog_ai.fitlog.training.repository.ExerciseRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ExerciseMapper exerciseMapper;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ExerciseMapper exerciseMapper) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseMapper = exerciseMapper;
    }

    @Override
    public List<ExerciseResponse> getAllExercises() {
        return exerciseRepository.findAll()
                .stream()
                .map(exerciseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ExerciseResponse getExerciseById(Long id) {
        return exerciseMapper.toResponse(
                exerciseRepository.findById(id)
                        .orElseThrow(()-> new ResourceNotFoundException("Exercise with id " + id + " not found")));
    }

    @Transactional
    @Override
    public ExerciseResponse createExercise(ExerciseRequest request) {
        Exercise exercise = exerciseMapper.toEntity(request);
        Exercise savedExercise = exerciseRepository.save(exercise);
        return exerciseMapper.toResponse(savedExercise);
    }

    @Transactional
    @Override
    public void deleteExercise(Long id) {
        Exercise exercise = exerciseRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Exercise with id " + id + " not found"));
        exerciseRepository.delete(exercise);
    }

    @Transactional
    @Override
    public ExerciseResponse updateExercise(Long id, ExerciseRequest request) {
        Exercise exercise = exerciseRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Exercise with id " + id + " not found"));

        exercise.setName(request.name());
        exercise.setDescription(request.description());
        exercise.setMuscleGroup(request.muscleGroup());
        exercise.setExerciseType(request.exerciseType());
        exercise.setEquipment(request.equipment());

        return exerciseMapper.toResponse(exerciseRepository.save(exercise));
    }
}

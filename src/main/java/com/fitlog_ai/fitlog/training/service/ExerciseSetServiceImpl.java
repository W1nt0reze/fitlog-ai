package com.fitlog_ai.fitlog.training.service;

import com.fitlog_ai.fitlog.common.exception.ResourceNotFoundException;
import com.fitlog_ai.fitlog.training.dto.ExerciseSetRequest;
import com.fitlog_ai.fitlog.training.dto.ExerciseSetResponse;
import com.fitlog_ai.fitlog.training.entity.Exercise;
import com.fitlog_ai.fitlog.training.entity.ExerciseSet;
import com.fitlog_ai.fitlog.training.entity.Workout;
import com.fitlog_ai.fitlog.training.mapper.ExerciseSetMapper;
import com.fitlog_ai.fitlog.training.repository.ExerciseRepository;
import com.fitlog_ai.fitlog.training.repository.ExerciseSetRepository;
import com.fitlog_ai.fitlog.training.repository.WorkoutRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseSetServiceImpl implements ExerciseSetService {

    private final ExerciseSetRepository exerciseSetRepository;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;
    private final ExerciseSetMapper exerciseSetMapper;

    public ExerciseSetServiceImpl(ExerciseSetRepository exerciseSetRepository,
                                  ExerciseRepository exerciseRepository,
                                  WorkoutRepository workoutRepository,
                                  ExerciseSetMapper exerciseSetMapper) {
        this.exerciseSetRepository = exerciseSetRepository;
        this.exerciseRepository = exerciseRepository;
        this.workoutRepository = workoutRepository;
        this.exerciseSetMapper = exerciseSetMapper;
    }

    @Override
    public List<ExerciseSetResponse> getAllExerciseSets() {
        return exerciseSetRepository.findAll()
                .stream()
                .map(exerciseSetMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExerciseSetResponse> getExerciseSetsByWorkoutId(Long workoutId) {

        workoutRepository.findById(workoutId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Workout with id " + workoutId + " not found"));

        return exerciseSetRepository.findByWorkoutId(workoutId)
                .stream()
                .map(exerciseSetMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExerciseSetResponse> getExerciseSetsByExerciseId(Long exerciseId) {

        exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Exercise with id " + exerciseId + " not found"));

        return exerciseSetRepository.findByExerciseId(exerciseId)
                .stream()
                .map(exerciseSetMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ExerciseSetResponse createExerciseSet(Long workoutId,
                                                 ExerciseSetRequest request) {

        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Workout with id " + workoutId + " not found"));

        Exercise exercise = exerciseRepository.findById(request.exerciseId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Exercise with id " + request.exerciseId() + " not found"));

        ExerciseSet exerciseSet = exerciseSetMapper.toEntity(request);

        exerciseSet.setWorkout(workout);
        exerciseSet.setExercise(exercise);

        ExerciseSet savedExerciseSet = exerciseSetRepository.save(exerciseSet);

        return exerciseSetMapper.toResponse(savedExerciseSet);
    }

    @Transactional
    @Override
    public void deleteExerciseSet(Long id) {

        ExerciseSet exerciseSet = exerciseSetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "ExerciseSet with id " + id + " not found"));

        exerciseSetRepository.delete(exerciseSet);
    }

    @Transactional
    @Override
    public ExerciseSetResponse updateExerciseSet(Long id,
                                                 ExerciseSetRequest request) {

        ExerciseSet exerciseSet = exerciseSetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "ExerciseSet with id " + id + " not found"));

        Exercise exercise = exerciseRepository.findById(request.exerciseId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Exercise with id " + request.exerciseId() + " not found"));

        exerciseSet.setSetNumber(request.setNumber());
        exerciseSet.setWeight(request.weight());
        exerciseSet.setReps(request.reps());
        exerciseSet.setRpe(request.rpe());
        exerciseSet.setRestSeconds(request.restSeconds());
        exerciseSet.setCompleted(request.completed());
        exerciseSet.setExercise(exercise);

        ExerciseSet savedExerciseSet = exerciseSetRepository.save(exerciseSet);

        return exerciseSetMapper.toResponse(savedExerciseSet);
    }
}
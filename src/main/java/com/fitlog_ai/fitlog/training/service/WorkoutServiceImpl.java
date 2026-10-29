package com.fitlog_ai.fitlog.training.service;

import com.fitlog_ai.fitlog.auth.entity.User;
import com.fitlog_ai.fitlog.auth.repository.UserRepository;
import com.fitlog_ai.fitlog.common.exception.ResourceNotFoundException;
import com.fitlog_ai.fitlog.training.dto.WorkoutRequest;
import com.fitlog_ai.fitlog.training.dto.WorkoutResponse;
import com.fitlog_ai.fitlog.training.entity.Workout;
import com.fitlog_ai.fitlog.training.mapper.WorkoutMapper;
import com.fitlog_ai.fitlog.training.repository.WorkoutRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class WorkoutServiceImpl implements WorkoutService {
    private final WorkoutRepository workoutRepository;
    private final WorkoutMapper workoutMapper;
    private final UserRepository userRepository;

    public WorkoutServiceImpl(WorkoutRepository workoutRepository, WorkoutMapper workoutMapper, UserRepository userRepository) {
        this.workoutRepository = workoutRepository;
        this.workoutMapper = workoutMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<WorkoutResponse> getAllWorkouts() {
        return workoutRepository.findAll()
                .stream()
                .map(workoutMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public WorkoutResponse getWorkoutById(Long id) {
        return workoutMapper.toResponse(
                workoutRepository.findById(id).
                        orElseThrow(()-> new ResourceNotFoundException("Workout with id " + id + " not found")));
    }

    @Transactional
    @Override
    public WorkoutResponse createWorkout(Long userId, WorkoutRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User with id " + userId + " not found"));

        Workout workout = workoutMapper.toEntity(request);

        workout.setUser(user);

        Workout savedWorkout = workoutRepository.save(workout);

        return workoutMapper.toResponse(savedWorkout);
    }

    @Transactional
    @Override
    public void deleteWorkout(Long id) {
        Workout workout = workoutRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Workout with id" + id + " not found"));
        workoutRepository.delete(workout);
    }

    @Transactional
    @Override
    public WorkoutResponse updateWorkout(Long id, WorkoutRequest request) {
        Workout workout = workoutRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Workout with id" + id + " not found"));

        workout.setTitle(request.title());
        workout.setDate(request.date());
        workout.setDurationMinutes(request.durationMinutes());
        workout.setDifficulty(request.difficulty());
        workout.setNotes(request.notes());

        return workoutMapper.toResponse(workout);
    }

    @Override
    public List<WorkoutResponse> getByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User with id " + userId + " not found");
        }

        return workoutRepository.findByUserId(userId)
                .stream()
                .map(workoutMapper::toResponse)
                .toList();
    }
}

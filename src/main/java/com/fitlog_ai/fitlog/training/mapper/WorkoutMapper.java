package com.fitlog_ai.fitlog.training.mapper;

import com.fitlog_ai.fitlog.training.dto.WorkoutRequest;
import com.fitlog_ai.fitlog.training.dto.WorkoutResponse;
import com.fitlog_ai.fitlog.training.entity.Workout;
import org.springframework.stereotype.Component;

@Component
public class WorkoutMapper {
    public Workout toEntity(WorkoutRequest request) {
        Workout workout = new Workout();

        workout.setTitle(request.title());
        workout.setDate(request.date());
        workout.setDurationMinutes(request.durationMinutes());
        workout.setDifficulty(request.difficulty());
        workout.setNotes(request.notes());
        return workout;
    }

    public WorkoutResponse toResponse(Workout workout) {
        return new WorkoutResponse(
                workout.getId(),
                workout.getTitle(),
                workout.getDate(),
                workout.getDurationMinutes(),
                workout.getDifficulty(),
                workout.getNotes()
        );
    }
}

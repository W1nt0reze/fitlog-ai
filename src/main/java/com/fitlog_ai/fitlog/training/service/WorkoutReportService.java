package com.fitlog_ai.fitlog.training.service;

import com.fitlog_ai.fitlog.auth.repository.UserRepository;
import com.fitlog_ai.fitlog.common.exception.ResourceNotFoundException;
import com.fitlog_ai.fitlog.training.dto.WorkoutReport;
import com.fitlog_ai.fitlog.training.repository.WorkoutRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Service
@Transactional(readOnly = true)
public class WorkoutReportService {
    private final WorkoutRepository workouts;
    private final UserRepository users;

    public WorkoutReportService(WorkoutRepository workouts, UserRepository users) {
        this.workouts = workouts;
        this.users = users;
    }

    public WorkoutReport report(Long userId, LocalDate from, LocalDate to) {
        if (from == null || to == null || from.isAfter(to)) {
            throw new IllegalArgumentException("from must be on or before to");
        }
        if (!users.existsById(userId)) {
            throw new ResourceNotFoundException("User with id " + userId + " not found");
        }
        return workouts.summarize(userId, from, to);
    }
}

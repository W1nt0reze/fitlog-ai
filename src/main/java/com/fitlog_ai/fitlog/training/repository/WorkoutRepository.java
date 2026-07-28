package com.fitlog_ai.fitlog.training.repository;

import com.fitlog_ai.fitlog.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fitlog_ai.fitlog.training.entity.Workout;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findByUserId(Long userId);
}

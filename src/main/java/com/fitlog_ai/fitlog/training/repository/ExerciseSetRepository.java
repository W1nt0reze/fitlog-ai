package com.fitlog_ai.fitlog.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fitlog_ai.fitlog.training.entity.ExerciseSet;
import com.fitlog_ai.fitlog.training.entity.Workout;

import java.util.List;

public interface ExerciseSetRepository extends JpaRepository<ExerciseSet, Long> {
    List<ExerciseSet> findByWorkout(Workout workout);
}

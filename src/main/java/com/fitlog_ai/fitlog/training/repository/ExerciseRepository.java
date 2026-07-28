package com.fitlog_ai.fitlog.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fitlog_ai.fitlog.training.entity.Exercise;
import com.fitlog_ai.fitlog.training.enums.MuscleGroup;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findByMuscleGroup(MuscleGroup muscleGroup);
}

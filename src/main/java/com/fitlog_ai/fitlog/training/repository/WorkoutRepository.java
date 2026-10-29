package com.fitlog_ai.fitlog.training.repository;

import com.fitlog_ai.fitlog.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fitlog_ai.fitlog.training.entity.Workout;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findByUserId(Long userId);

    @org.springframework.data.jpa.repository.Query("""
        select new com.fitlog_ai.fitlog.training.dto.WorkoutReport(
            count(w), coalesce(sum(w.durationMinutes), 0L))
        from Workout w
        where w.user.id = :userId and w.date between :from and :to
        """)
    com.fitlog_ai.fitlog.training.dto.WorkoutReport summarize(
        @org.springframework.data.repository.query.Param("userId") Long userId,
        @org.springframework.data.repository.query.Param("from") java.time.LocalDate from,
        @org.springframework.data.repository.query.Param("to") java.time.LocalDate to);
}

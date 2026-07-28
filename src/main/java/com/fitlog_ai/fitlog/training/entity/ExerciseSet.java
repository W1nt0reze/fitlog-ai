package com.fitlog_ai.fitlog.training.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "exercise_sets")
@Getter
@Setter
public class ExerciseSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer setNumber;

    @Column(nullable = false)
    private Double weight;

    @Column(nullable = false)
    private Integer reps;

    @Column()
    private Integer rpe;

    @Column()
    private Integer restSeconds;

    @Column()
    private boolean completed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_id", nullable = false)
    private Workout workout;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;
}

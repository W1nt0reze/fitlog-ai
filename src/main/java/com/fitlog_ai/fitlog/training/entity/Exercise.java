package com.fitlog_ai.fitlog.training.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.fitlog_ai.fitlog.training.enums.Equipment;
import com.fitlog_ai.fitlog.training.enums.ExerciseType;
import com.fitlog_ai.fitlog.training.enums.MuscleGroup;

@Entity
@Table(name="exercises")
@Getter
@Setter
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column()
    private String description;

    @Enumerated(EnumType.STRING)
    @Column()
    private MuscleGroup muscleGroup;

    @Enumerated(EnumType.STRING)
    @Column()
    private ExerciseType exerciseType;

    @Enumerated(EnumType.STRING)
    @Column()
    private Equipment equipment;

    @Column(nullable = false)
    private Boolean custom = false;
}

package com.fitlog_ai.fitlog.training.entity;

import com.fitlog_ai.fitlog.auth.entity.User;
import com.fitlog_ai.fitlog.training.enums.Difficulty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="workouts")
@Getter
@Setter
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDate date;

    @Column
    private Integer durationMinutes;

    @Enumerated(EnumType.STRING)
    @Column
    private Difficulty difficulty;

    @Column(length = 1000)
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

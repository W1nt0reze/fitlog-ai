package com.fitlog_ai.fitlog.auth.entity;

import com.fitlog_ai.fitlog.auth.enums.ActivityLevel;
import com.fitlog_ai.fitlog.auth.enums.Goal;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;


@Entity
@Table(name="users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Integer height;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column()
    private Goal goal;

    @Enumerated(EnumType.STRING)
    @Column()
    private ActivityLevel activityLevel;

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();
}

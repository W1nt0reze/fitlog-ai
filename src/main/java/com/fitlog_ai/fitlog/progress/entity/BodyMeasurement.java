package com.fitlog_ai.fitlog.progress.entity;

import com.fitlog_ai.fitlog.auth.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "body_measurements")
@Getter
@Setter
public class BodyMeasurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column()
    private Double weightKg;

    @Column()
    private Double bodyFatPercent;

    @Column()
    private Double neckCm;

    @Column()
    private Double chestCm;

    @Column()
    private Double waistCm;

    @Column()
    private Double hipsCm;

    @Column()
    private Double bicepsCm;

    @Column()
    private Double forearmCm;

    @Column()
    private Double thighCm;

    @Column()
    private Double calfCm;

    @Column(length = 1000)
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

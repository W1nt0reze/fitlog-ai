package com.fitlog_ai.fitlog.progress.repository;

import com.fitlog_ai.fitlog.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fitlog_ai.fitlog.progress.entity.BodyMeasurement;

import java.util.List;

public interface BodyMeasurementsRepository extends JpaRepository<BodyMeasurement, Long> {
    List<BodyMeasurement> findByUserOrderByDateDesc(User user);
}

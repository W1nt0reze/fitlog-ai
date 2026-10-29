package com.fitlog_ai.fitlog.progress.service;

import com.fitlog_ai.fitlog.progress.dto.BodyMeasurementRequest;
import com.fitlog_ai.fitlog.progress.dto.BodyMeasurementResponse;

import java.util.List;

public interface BodyMeasurementService {
    BodyMeasurementResponse getBodyMeasurementById(Long id);

    BodyMeasurementResponse createBodyMeasurement(Long userId, BodyMeasurementRequest request);

    BodyMeasurementResponse updateBodyMeasurement(Long id, BodyMeasurementRequest request);

    void deleteBodyMeasurement(Long id);

    List<BodyMeasurementResponse> getByUserId(Long id);

}

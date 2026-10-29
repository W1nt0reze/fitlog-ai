package com.fitlog_ai.fitlog.progress.mapper;

import com.fitlog_ai.fitlog.progress.dto.BodyMeasurementRequest;
import com.fitlog_ai.fitlog.progress.dto.BodyMeasurementResponse;
import com.fitlog_ai.fitlog.progress.entity.BodyMeasurement;
import org.springframework.stereotype.Component;

@Component
public class BodyMeasurementMapper {
    public BodyMeasurement toEntity(BodyMeasurementRequest request) {
        BodyMeasurement bodyMeasurement = new BodyMeasurement();

        bodyMeasurement.setDate(request.date());
        bodyMeasurement.setWeightKg(request.weightKg());
        bodyMeasurement.setBodyFatPercent(request.bodyFatPercent());
        bodyMeasurement.setNeckCm(request.neckCm());
        bodyMeasurement.setChestCm(request.chestCm());
        bodyMeasurement.setWaistCm(request.waistCm());
        bodyMeasurement.setHipsCm(request.hipsCm());
        bodyMeasurement.setBicepsCm(request.bicepsCm());
        bodyMeasurement.setForearmCm(request.forearmCm());
        bodyMeasurement.setThighCm(request.thighCm());
        bodyMeasurement.setCalfCm(request.calfCm());
        bodyMeasurement.setNotes(request.notes());

        return bodyMeasurement;
    }

    public BodyMeasurementResponse toResponse(BodyMeasurement bodyMeasurement) {
        return new BodyMeasurementResponse(
                bodyMeasurement.getId(),
                bodyMeasurement.getDate(),
                bodyMeasurement.getWeightKg(),
                bodyMeasurement.getBodyFatPercent(),
                bodyMeasurement.getNeckCm(),
                bodyMeasurement.getChestCm(),
                bodyMeasurement.getWaistCm(),
                bodyMeasurement.getHipsCm(),
                bodyMeasurement.getBicepsCm(),
                bodyMeasurement.getForearmCm(),
                bodyMeasurement.getThighCm(),
                bodyMeasurement.getCalfCm(),
                bodyMeasurement.getNotes()
        );
    }
}

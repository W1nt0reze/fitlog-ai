package com.fitlog_ai.fitlog.progress.dto;

import java.time.LocalDate;

public record BodyMeasurementResponse(
        Long id,
        LocalDate date,
        Double weightKg,
        Double bodyFatPercent,
        Double neckCm,
        Double chestCm,
        Double waistCm,
        Double hipsCm,
        Double bicepsCm,
        Double forearmCm,
        Double thighCm,
        Double calfCm,
        String notes
) {
}

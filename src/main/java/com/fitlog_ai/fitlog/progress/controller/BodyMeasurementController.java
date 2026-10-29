package com.fitlog_ai.fitlog.progress.controller;

import com.fitlog_ai.fitlog.progress.dto.BodyMeasurementRequest;
import com.fitlog_ai.fitlog.progress.dto.BodyMeasurementResponse;
import com.fitlog_ai.fitlog.progress.service.BodyMeasurementService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/body-measurements")
public class BodyMeasurementController {

    private final BodyMeasurementService bodyMeasurementService;

    public BodyMeasurementController(
            BodyMeasurementService bodyMeasurementService
    ) {
        this.bodyMeasurementService = bodyMeasurementService;
    }

    @GetMapping("/{id}")
    public BodyMeasurementResponse getById(@PathVariable Long id) {
        return bodyMeasurementService.getBodyMeasurementById(id);
    }

    @GetMapping("/user/{userId}")
    public List<BodyMeasurementResponse> getByUserId(
            @PathVariable Long userId
    ) {
        return bodyMeasurementService.getByUserId(userId);
    }

    @PostMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public BodyMeasurementResponse create(
            @PathVariable Long userId,
            @RequestBody BodyMeasurementRequest request
    ) {
        return bodyMeasurementService.createBodyMeasurement(userId, request);
    }

    @PutMapping("/{id}")
    public BodyMeasurementResponse update(
            @PathVariable Long id,
            @RequestBody BodyMeasurementRequest request
    ) {
        return bodyMeasurementService.updateBodyMeasurement(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        bodyMeasurementService.deleteBodyMeasurement(id);
    }
}

package com.fitlog_ai.fitlog.training.controller;

import com.fitlog_ai.fitlog.training.dto.WorkoutReport;
import com.fitlog_ai.fitlog.training.service.WorkoutReportService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/workouts/user/{userId}/report")
public class WorkoutReportController {
    private final WorkoutReportService service;

    public WorkoutReportController(WorkoutReportService service) { this.service = service; }

    @GetMapping
    public WorkoutReport report(@PathVariable Long userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return service.report(userId, from, to);
    }
}

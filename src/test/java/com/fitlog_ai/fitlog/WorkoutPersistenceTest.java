package com.fitlog_ai.fitlog;

import com.fitlog_ai.fitlog.auth.entity.User;
import com.fitlog_ai.fitlog.auth.repository.UserRepository;
import com.fitlog_ai.fitlog.training.dto.WorkoutRequest;
import com.fitlog_ai.fitlog.training.repository.WorkoutRepository;
import com.fitlog_ai.fitlog.training.service.WorkoutService;
import com.fitlog_ai.fitlog.training.service.WorkoutReportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WorkoutPersistenceTest {
    @Autowired WorkoutService service;
    @Autowired WorkoutReportService reports;
    @Autowired UserRepository users;
    @Autowired WorkoutRepository workouts;
    @Autowired PlatformTransactionManager transactions;

    @Test
    void dirtyCheckingAndInclusiveReport() {
        var date = LocalDate.of(2026, 10, 29);
        var user = new User();
        user.setName("Test");
        user.setEmail("persistence@example.test");
        user.setPassword("test-hash");
        user.setHeight(180);
        user.setBirthDate(LocalDate.of(2000, 1, 1));
        user = users.save(user);
        var created = service.createWorkout(user.getId(),
                new WorkoutRequest("Before", date, 20, null, null));
        service.updateWorkout(created.id(), new WorkoutRequest("After", date, 45, null, null));
        var tx = new TransactionTemplate(transactions);
        assertEquals("After", tx.execute(s -> workouts.findById(created.id()).orElseThrow().getTitle()));
        var report = reports.report(user.getId(), date, date);
        assertEquals(1, report.workoutCount());
        assertEquals(45, report.totalDurationMinutes());
        assertEquals(0, reports.report(user.getId(), date.plusDays(1), date.plusDays(2)).workoutCount());
        assertThrows(IllegalArgumentException.class, () -> reports.report(1L, date, date.minusDays(1)));
        service.deleteWorkout(created.id());
        users.deleteById(user.getId());
    }
}

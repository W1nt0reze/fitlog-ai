package com.fitlog_ai.fitlog;

import com.fitlog_ai.fitlog.auth.repository.UserRepository;
import com.fitlog_ai.fitlog.common.exception.ResourceNotFoundException;
import com.fitlog_ai.fitlog.training.mapper.WorkoutMapper;
import com.fitlog_ai.fitlog.training.repository.WorkoutRepository;
import com.fitlog_ai.fitlog.training.service.WorkoutServiceImpl;
import com.fitlog_ai.fitlog.training.dto.WorkoutRequest;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WorkoutServiceTest {
    @Test
    void missingUserDoesNotSaveWorkout() {
        var users = mock(UserRepository.class);
        var workouts = mock(WorkoutRepository.class);
        when(users.findById(42L)).thenReturn(Optional.empty());
        var service = new WorkoutServiceImpl(workouts, new WorkoutMapper(), users);
        assertThrows(ResourceNotFoundException.class, () -> service.createWorkout(42L,
                new WorkoutRequest("Test", LocalDate.now(), 30, null, null)));
        verifyNoInteractions(workouts);
    }
}

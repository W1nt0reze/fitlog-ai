package com.fitlog_ai.fitlog;

import com.fitlog_ai.fitlog.training.controller.WorkoutController;
import com.fitlog_ai.fitlog.training.service.WorkoutService;
import com.fitlog_ai.fitlog.common.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class WorkoutHttpTest {
    @Test
    void invalidBodyReturns400BeforeCallingService() throws Exception {
        var service = mock(WorkoutService.class);
        var mvc = MockMvcBuilders.standaloneSetup(new WorkoutController(service))
                .setControllerAdvice(new GlobalExceptionHandler()).build();
        mvc.perform(post("/api/workouts/user/1").contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"\",\"durationMinutes\":-1}"))
                .andExpect(status().isBadRequest());
        verifyNoInteractions(service);
    }

    @Test
    void userIdIsBoundFromPath() throws Exception {
        var service = mock(WorkoutService.class);
        var mvc = MockMvcBuilders.standaloneSetup(new WorkoutController(service)).build();
        mvc.perform(get("/api/workouts/user/42")).andExpect(status().isOk());
        verify(service).getByUserId(42L);
    }
}

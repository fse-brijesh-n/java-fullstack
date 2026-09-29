package com.example.loggingservice.application.service;

import com.example.loggingservice.domain.model.LogEntry;
import com.example.loggingservice.domain.port.in.SaveLogUseCase;
import com.example.loggingservice.domain.port.out.LogRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SaveLogServiceTest {

    @Mock
    private LogRepositoryPort logRepositoryPort;

    private SaveLogService saveLogService;

    @BeforeEach
    void setUp() {
        saveLogService = new SaveLogService(logRepositoryPort);
    }

    @Test
    void savesLogEntryWithTimestamp() {
        when(logRepositoryPort.save(any(LogEntry.class))).thenAnswer(invocation -> {
            LogEntry entry = invocation.getArgument(0);
            entry.setId(1L);
            return entry;
        });

        LogEntry result = saveLogService.save(
                new SaveLogUseCase.SaveLogCommand("auth-service", LogEntry.Level.INFO, "user registered"));

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getSourceService()).isEqualTo("auth-service");
        assertThat(result.getLevel()).isEqualTo(LogEntry.Level.INFO);
        assertThat(result.getMessage()).isEqualTo("user registered");
        assertThat(result.getTimestamp()).isNotNull();
    }
}

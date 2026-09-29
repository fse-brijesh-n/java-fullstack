package com.example.loggingservice.application.service;

import com.example.loggingservice.domain.model.LogEntry;
import com.example.loggingservice.domain.port.out.LogRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QueryLogsServiceTest {

    @Mock
    private LogRepositoryPort logRepositoryPort;

    private QueryLogsService queryLogsService;

    @BeforeEach
    void setUp() {
        queryLogsService = new QueryLogsService(logRepositoryPort);
    }

    @Test
    void findAllDelegatesToRepositoryPort() {
        LogEntry entry = new LogEntry(1L, "auth-service", LogEntry.Level.INFO, "hello", Instant.now());
        when(logRepositoryPort.findAll()).thenReturn(List.of(entry));

        assertThat(queryLogsService.findAll()).containsExactly(entry);
    }

    @Test
    void findBySourceServiceDelegatesToRepositoryPort() {
        LogEntry entry = new LogEntry(2L, "document-service", LogEntry.Level.WARN, "slow upload", Instant.now());
        when(logRepositoryPort.findBySourceService("document-service")).thenReturn(List.of(entry));

        assertThat(queryLogsService.findBySourceService("document-service")).containsExactly(entry);
    }
}

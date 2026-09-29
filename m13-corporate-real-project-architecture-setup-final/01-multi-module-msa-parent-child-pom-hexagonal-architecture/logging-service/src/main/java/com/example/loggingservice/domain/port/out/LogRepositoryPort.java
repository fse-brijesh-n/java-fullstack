package com.example.loggingservice.domain.port.out;

import com.example.loggingservice.domain.model.LogEntry;

import java.util.List;

public interface LogRepositoryPort {

    LogEntry save(LogEntry logEntry);

    List<LogEntry> findAll();

    List<LogEntry> findBySourceService(String sourceService);
}

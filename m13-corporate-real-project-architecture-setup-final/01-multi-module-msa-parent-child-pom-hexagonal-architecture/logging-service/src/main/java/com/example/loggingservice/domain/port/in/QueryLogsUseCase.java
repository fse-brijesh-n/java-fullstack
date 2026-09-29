package com.example.loggingservice.domain.port.in;

import com.example.loggingservice.domain.model.LogEntry;

import java.util.List;

public interface QueryLogsUseCase {

    List<LogEntry> findAll();

    List<LogEntry> findBySourceService(String sourceService);
}

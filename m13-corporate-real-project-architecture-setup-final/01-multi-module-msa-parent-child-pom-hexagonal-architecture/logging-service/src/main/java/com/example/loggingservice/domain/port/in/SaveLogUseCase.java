package com.example.loggingservice.domain.port.in;

import com.example.loggingservice.domain.model.LogEntry;

public interface SaveLogUseCase {

    LogEntry save(SaveLogCommand command);

    record SaveLogCommand(String sourceService, LogEntry.Level level, String message) {
    }
}

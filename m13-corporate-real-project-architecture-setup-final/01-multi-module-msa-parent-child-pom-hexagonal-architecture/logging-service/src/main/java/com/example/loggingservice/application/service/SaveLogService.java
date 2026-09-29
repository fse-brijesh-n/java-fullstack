package com.example.loggingservice.application.service;

import com.example.loggingservice.domain.model.LogEntry;
import com.example.loggingservice.domain.port.in.SaveLogUseCase;
import com.example.loggingservice.domain.port.out.LogRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class SaveLogService implements SaveLogUseCase {

    private final LogRepositoryPort logRepositoryPort;

    public SaveLogService(LogRepositoryPort logRepositoryPort) {
        this.logRepositoryPort = logRepositoryPort;
    }

    @Override
    public LogEntry save(SaveLogCommand command) {
        LogEntry entry = new LogEntry(null, command.sourceService(), command.level(), command.message(), Instant.now());
        return logRepositoryPort.save(entry);
    }
}

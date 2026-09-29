package com.example.loggingservice.application.service;

import com.example.loggingservice.domain.model.LogEntry;
import com.example.loggingservice.domain.port.in.QueryLogsUseCase;
import com.example.loggingservice.domain.port.out.LogRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryLogsService implements QueryLogsUseCase {

    private final LogRepositoryPort logRepositoryPort;

    public QueryLogsService(LogRepositoryPort logRepositoryPort) {
        this.logRepositoryPort = logRepositoryPort;
    }

    @Override
    public List<LogEntry> findAll() {
        return logRepositoryPort.findAll();
    }

    @Override
    public List<LogEntry> findBySourceService(String sourceService) {
        return logRepositoryPort.findBySourceService(sourceService);
    }
}

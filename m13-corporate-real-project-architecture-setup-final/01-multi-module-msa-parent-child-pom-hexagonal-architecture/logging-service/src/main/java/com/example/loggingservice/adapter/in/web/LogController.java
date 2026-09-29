package com.example.loggingservice.adapter.in.web;

import com.example.common.dto.ApiResponse;
import com.example.loggingservice.domain.model.LogEntry;
import com.example.loggingservice.domain.port.in.QueryLogsUseCase;
import com.example.loggingservice.domain.port.in.SaveLogUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LogController {

    private final SaveLogUseCase saveLogUseCase;
    private final QueryLogsUseCase queryLogsUseCase;

    public LogController(SaveLogUseCase saveLogUseCase, QueryLogsUseCase queryLogsUseCase) {
        this.saveLogUseCase = saveLogUseCase;
        this.queryLogsUseCase = queryLogsUseCase;
    }

    @PostMapping("/api/logs")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<LogEntry> saveLog(@Valid @RequestBody LogEntryRequest request) {
        LogEntry saved = saveLogUseCase.save(new SaveLogUseCase.SaveLogCommand(
                request.getSourceService(), request.getLevel(), request.getMessage()));
        return ApiResponse.ok("Log entry recorded", saved);
    }

    @GetMapping("/api/logs")
    public ApiResponse<List<LogEntry>> getLogs(@RequestParam(required = false) String sourceService) {
        List<LogEntry> logs = (sourceService == null)
                ? queryLogsUseCase.findAll()
                : queryLogsUseCase.findBySourceService(sourceService);
        return ApiResponse.ok(logs);
    }
}

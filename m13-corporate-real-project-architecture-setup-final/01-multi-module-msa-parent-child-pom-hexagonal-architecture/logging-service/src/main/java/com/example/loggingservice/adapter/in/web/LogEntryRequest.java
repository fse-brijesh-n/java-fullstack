package com.example.loggingservice.adapter.in.web;

import com.example.loggingservice.domain.model.LogEntry;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LogEntryRequest {

    @NotBlank
    private String sourceService;

    @NotNull
    private LogEntry.Level level;

    @NotBlank
    private String message;

    public String getSourceService() {
        return sourceService;
    }

    public void setSourceService(String sourceService) {
        this.sourceService = sourceService;
    }

    public LogEntry.Level getLevel() {
        return level;
    }

    public void setLevel(LogEntry.Level level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

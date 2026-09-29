package com.example.loggingservice.domain.model;

import java.time.Instant;

/**
 * Core domain object representing a single log event pushed by another service.
 */
public class LogEntry {

    public enum Level { DEBUG, INFO, WARN, ERROR }

    private Long id;
    private String sourceService;
    private Level level;
    private String message;
    private Instant timestamp;

    public LogEntry() {
    }

    public LogEntry(Long id, String sourceService, Level level, String message, Instant timestamp) {
        this.id = id;
        this.sourceService = sourceService;
        this.level = level;
        this.message = message;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSourceService() {
        return sourceService;
    }

    public void setSourceService(String sourceService) {
        this.sourceService = sourceService;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}

package com.example.loggingservice.adapter.out.persistence;

import com.example.loggingservice.domain.model.LogEntry;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "log_entries")
public class LogEntryJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sourceService;

    @Enumerated(EnumType.STRING)
    private LogEntry.Level level;

    private String message;
    private Instant timestamp;

    public LogEntryJpaEntity() {
    }

    public LogEntryJpaEntity(Long id, String sourceService, LogEntry.Level level, String message, Instant timestamp) {
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

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}

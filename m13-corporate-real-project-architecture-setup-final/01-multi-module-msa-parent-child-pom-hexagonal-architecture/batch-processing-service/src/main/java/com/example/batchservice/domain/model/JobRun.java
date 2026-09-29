package com.example.batchservice.domain.model;

import java.time.Instant;

/**
 * Core domain object representing a single execution of a named batch job.
 * Framework-free - persistence mapping lives in the outbound adapter.
 */
public class JobRun {

    public enum Status { RUNNING, COMPLETED, FAILED }

    private Long id;
    private String jobName;
    private Status status;
    private Instant startedAt;
    private Instant finishedAt;
    private String message;

    public JobRun() {
    }

    public JobRun(Long id, String jobName, Status status, Instant startedAt, Instant finishedAt, String message) {
        this.id = id;
        this.jobName = jobName;
        this.status = status;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.message = message;
    }

    public static JobRun start(String jobName) {
        return new JobRun(null, jobName, Status.RUNNING, Instant.now(), null, "Job started");
    }

    public JobRun complete(String message) {
        this.status = Status.COMPLETED;
        this.finishedAt = Instant.now();
        this.message = message;
        return this;
    }

    public JobRun fail(String message) {
        this.status = Status.FAILED;
        this.finishedAt = Instant.now();
        this.message = message;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Instant getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Instant startedAt) {
        this.startedAt = startedAt;
    }

    public Instant getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(Instant finishedAt) {
        this.finishedAt = finishedAt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

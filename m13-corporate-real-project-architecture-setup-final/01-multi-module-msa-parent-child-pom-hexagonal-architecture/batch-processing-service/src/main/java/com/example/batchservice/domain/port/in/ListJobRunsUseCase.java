package com.example.batchservice.domain.port.in;

import com.example.batchservice.domain.model.JobRun;

import java.util.List;

/**
 * Inbound port (use case): query the history of job runs.
 */
public interface ListJobRunsUseCase {

    List<JobRun> listAll();

    List<JobRun> listByJobName(String jobName);
}

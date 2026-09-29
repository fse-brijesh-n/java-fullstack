package com.example.batchservice.domain.port.out;

import com.example.batchservice.domain.model.JobRun;

import java.util.List;

/**
 * Outbound port - persistence contract needed by the domain/application layer.
 */
public interface JobRunRepositoryPort {

    JobRun save(JobRun jobRun);

    List<JobRun> findAll();

    List<JobRun> findByJobName(String jobName);
}

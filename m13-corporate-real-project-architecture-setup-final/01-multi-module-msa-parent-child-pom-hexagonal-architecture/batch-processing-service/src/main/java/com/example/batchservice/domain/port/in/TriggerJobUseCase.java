package com.example.batchservice.domain.port.in;

import com.example.batchservice.domain.model.JobRun;

/**
 * Inbound port (use case): trigger a batch job by name. Invoked either from a REST
 * endpoint (adapter/in/web) or from the scheduled runner (adapter/in/scheduler).
 */
public interface TriggerJobUseCase {

    JobRun triggerJob(String jobName);
}

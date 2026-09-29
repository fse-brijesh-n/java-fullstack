package com.example.batchservice.adapter.in.web;

import com.example.batchservice.domain.model.JobRun;
import com.example.batchservice.domain.port.in.ListJobRunsUseCase;
import com.example.batchservice.domain.port.in.TriggerJobUseCase;
import com.example.common.dto.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BatchJobController {

    private final TriggerJobUseCase triggerJobUseCase;
    private final ListJobRunsUseCase listJobRunsUseCase;

    public BatchJobController(TriggerJobUseCase triggerJobUseCase, ListJobRunsUseCase listJobRunsUseCase) {
        this.triggerJobUseCase = triggerJobUseCase;
        this.listJobRunsUseCase = listJobRunsUseCase;
    }

    @PostMapping("/api/batch/jobs/{jobName}/trigger")
    public ApiResponse<JobRun> trigger(@PathVariable String jobName) {
        return ApiResponse.ok("Job triggered", triggerJobUseCase.triggerJob(jobName));
    }

    @GetMapping("/api/batch/jobs")
    public ApiResponse<List<JobRun>> list(@RequestParam(required = false) String jobName) {
        List<JobRun> runs = (jobName == null)
                ? listJobRunsUseCase.listAll()
                : listJobRunsUseCase.listByJobName(jobName);
        return ApiResponse.ok(runs);
    }
}

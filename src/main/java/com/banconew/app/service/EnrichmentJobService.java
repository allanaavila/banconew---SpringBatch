package com.banconew.app.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrichmentJobService {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job enrichmentJob;

    public void runJob() throws Exception {
        JobParameters parameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(enrichmentJob, parameters);
    }
}

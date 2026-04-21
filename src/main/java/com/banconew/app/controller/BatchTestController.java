package com.banconew.app.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batch")
public class BatchTestController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job enrichmentJob;

    @GetMapping("/run")
    public String runJob() {
        try {
            JobParameters params = new JobParametersBuilder()
                    .addLong("startAt", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(enrichmentJob, params);
            return "Job disparado com sucesso! Verifique o console.";
        } catch (Exception e) {
            return "Erro ao disparar Job: " + e.getMessage();
        }
    }
}
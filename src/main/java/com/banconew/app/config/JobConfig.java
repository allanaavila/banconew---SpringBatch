package com.banconew.app.config;

import com.banconew.app.dto.RestrictiveDTO;
import com.banconew.domain.model.RestrictiveModel;
import com.banconew.infra.batch.processor.EnrichmentProcessor;
import com.banconew.infra.batch.writer.EnrichmentWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
public class JobConfig {
    @Value("${app.batch.chunk-size}")
    private int chunkSize;

    @Bean
    public Step enrichmentStep(JobRepository jobRepository,
                               PlatformTransactionManager transactionManager,
                               RepositoryItemReader<RestrictiveModel> reader,
                               EnrichmentProcessor processor,
                               EnrichmentWriter writer) {
        return new StepBuilder("enrichmentStep", jobRepository)
                .<RestrictiveModel, RestrictiveDTO>chunk(chunkSize, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job enrichmentJob(JobRepository jobRepository, Step enrichmentStep) {
        return new JobBuilder("enrichmentJob", jobRepository)
                .start(enrichmentStep)
                .build();
    }
}

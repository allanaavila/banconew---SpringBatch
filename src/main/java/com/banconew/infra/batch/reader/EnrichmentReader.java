package com.banconew.infra.batch.reader;

import com.banconew.domain.model.RestrictiveModel;
import com.banconew.infra.repository.RestrictiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Slf4j
@Component
public class EnrichmentReader {

    private final RestrictiveRepository repository;

    public EnrichmentReader(RestrictiveRepository repository) {
        this.repository = repository;
    }

    @Bean
    public RepositoryItemReader<RestrictiveModel> reader() {
        return new RepositoryItemReaderBuilder<RestrictiveModel>()
                .name("enrichmentReader")
                .repository(this.repository)
                .methodName("findByStatus_Id")
                .arguments(Collections.singletonList(2L))
                .sorts(Collections.singletonMap("id", Sort.Direction.ASC))
                .pageSize(10)
                .build();
    }
}
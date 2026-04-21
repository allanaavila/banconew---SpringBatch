package com.banconew.infra.batch.writer;

import com.banconew.app.dto.RestrictiveDTO;
import com.banconew.domain.model.StatusModel;
import com.banconew.infra.repository.RestrictiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class EnrichmentWriter implements ItemWriter<RestrictiveDTO> {

    @Autowired
    private RestrictiveRepository repository;

    @Override
    @Transactional
    public void write(Chunk<? extends RestrictiveDTO> chunk) throws Exception {
        for (RestrictiveDTO dto : chunk.getItems()) {
            StatusModel statusEnum = StatusModel.fromId(dto.getStatusId());

            repository.updateStatus(dto.getCustomerId(), statusEnum);
        }
    }
}
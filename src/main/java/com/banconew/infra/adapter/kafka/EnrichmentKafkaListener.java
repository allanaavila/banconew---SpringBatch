package com.banconew.infra.adapter.kafka;

import com.banconew.app.dto.KafkaEnvelopeDTO;
import com.banconew.domain.model.CustomerModel;
import com.banconew.domain.model.StatusModel;
import com.banconew.infra.mappers.CustomerModelMapper;
import com.banconew.infra.repository.CustomerRepository;
import com.banconew.infra.repository.RestrictiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class EnrichmentKafkaListener {

    @Autowired
    private RestrictiveRepository restrictiveRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerModelMapper customerMapper;

    @Transactional
    @KafkaListener(topics = "banconew-request-enriquecimento", groupId = "banconew-group")
    public void consume(KafkaEnvelopeDTO envelope) {
        String customerId = envelope.getCustomer().getId();
        log.info("#### [KAFKA] Recebida mensagem para o Cliente ID: {} ####", customerId);

        if (restrictiveRepository.existsByCustomerId(customerId)) {
            log.info("ID {} localizado. Criando dados financeiros...", customerId);

            CustomerModel customerModel = customerMapper.toModel(envelope.getCustomer());

            customerRepository.save(customerModel);

            restrictiveRepository.updateStatus(customerId, StatusModel.ENRICHED);

            log.info("SUCESSO: Tabelas populadas e status atualizado para ENRICHED.");
        } else {
            log.warn("AVISO: Cliente ID {} não encontrado em restrictive. Ignorado.", customerId);
        }
    }
}
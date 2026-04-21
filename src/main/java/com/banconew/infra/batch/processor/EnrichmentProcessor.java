package com.banconew.infra.batch.processor;

import com.banconew.app.dto.CardContractDTO;
import com.banconew.app.dto.CardDTO;
import com.banconew.app.dto.RestrictiveDTO;
import com.banconew.domain.model.CardContractModel;
import com.banconew.domain.model.CustomerModel;
import com.banconew.domain.model.RestrictiveModel;
import com.banconew.domain.model.StatusModel;
import com.banconew.infra.mappers.RestrictiveModelMapper;
import com.banconew.infra.repository.CardRepository;
import com.banconew.infra.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class EnrichmentProcessor implements ItemProcessor<RestrictiveModel, RestrictiveDTO> {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RestrictiveModelMapper restrictiveMapper;

    @Autowired
    private KafkaTemplate<String, CardContractDTO> kafkaTemplate;

    @Value("${spring.kafka.topic.request}")
    private String topicRequest;

    @Override
    public RestrictiveDTO process(RestrictiveModel itemModel) {
        String customerId = itemModel.getCustomer().getId();
        log.info("#### [BATCH] Iniciando Processamento - Cliente: {} ####", customerId);

        RestrictiveDTO dto = restrictiveMapper.toDTO(itemModel);

        return customerRepository.findById(customerId)
                .map(customer -> performEnrichment(customer, dto))
                .orElseGet(() -> handleCustomerNotFound(customerId, dto));
    }

    /**
     * Coordena o enriquecimento se o cliente for encontrado
     */
    private RestrictiveDTO performEnrichment(CustomerModel customer, RestrictiveDTO dto) {
        List<CardContractModel> contracts = customer.getCardContracts();

        if (contracts == null || contracts.isEmpty()) {
            log.warn("AVISO: Cliente {} sem contratos ativos.", customer.getId());
            dto.setStatusId(StatusModel.ERROR.getId());
            return dto;
        }

        // Processamos o primeiro contrato disponível
        CardContractModel contractModel = contracts.get(0);
        CardContractDTO contractRequest = convertToCardContractDTO(contractModel, customer.getId());

        sendToKafka(contractRequest);

        dto.setStatusId(StatusModel.PROCESSED.getId());
        return dto;
    }

    /**
     * Converte a Model para o DTO que será enviado ao Kafka
     */
    private CardContractDTO convertToCardContractDTO(CardContractModel model, String customerId) {
        List<CardDTO> cardDtos = model.getCards().stream()
                .map(card -> CardDTO.builder()
                        .cardNumber(card.getCardNumber())
                        .brand(card.getBrand())
                        .cardLimit(card.getCardLimit() != null ? card.getCardLimit() : BigDecimal.ZERO)
                        .cardBalance(card.getCardBalance() != null ? card.getCardBalance() : BigDecimal.ZERO)
                        .cardStatus(card.getCardStatus())
                        .build())
                .collect(Collectors.toList());

        return CardContractDTO.builder()
                .contractId(model.getContractId())
                .customerId(customerId)
                .status(mapStatus(model.getStatus()))
                .updateTime(model.getUpdateTime())
                .cards(cardDtos)
                .build();
    }

    /**
     * Mapeia a String de status para o Enum StatusModel com segurança
     */
    private StatusModel mapStatus(String statusStr) {
        try {
            return (statusStr != null) ? StatusModel.valueOf(statusStr.toUpperCase()) : StatusModel.ACTIVED;
        } catch (IllegalArgumentException e) {
            log.error("Status inválido encontrado: {}. Usando padrão ACTIVED.", statusStr);
            return StatusModel.ACTIVED;
        }
    }

    /**
     * Realiza o envio para o Kafka
     */
    private void sendToKafka(CardContractDTO payload) {
        log.info("#### [KAFKA] Enviando Contrato: {} para o tópico: {} ####", payload.getContractId(), topicRequest);
        kafkaTemplate.send(topicRequest, payload);
    }

    /**
     * Trata o cenário de cliente ausente no banco
     */
    private RestrictiveDTO handleCustomerNotFound(String customerId, RestrictiveDTO dto) {
        log.error("ERRO: Cliente {} não localizado na base de dados.", customerId);
        dto.setStatusId(StatusModel.ERROR.getId());
        return dto;
    }
}
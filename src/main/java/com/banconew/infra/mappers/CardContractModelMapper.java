package com.banconew.infra.mappers;

import com.banconew.app.dto.CardContractDTO;
import com.banconew.domain.model.CardContractModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CustomerModelMapper.class, CardModelMapper.class})
public interface CardContractModelMapper {

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "cardNumber", source = "card.cardNumber")
    CardContractDTO toDTO(CardContractModel model);

    @Mapping(target = "customer.id", source = "customerId")
    @Mapping(target = "contractId", source = "contractId")
    CardContractModel toModel(CardContractDTO dto);
}

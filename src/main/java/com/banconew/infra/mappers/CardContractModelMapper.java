package com.banconew.infra.mappers;

import com.banconew.app.dto.CardContractDTO;
import com.banconew.domain.model.CardContractModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CardContractModelMapper {

    CardContractModel toModel(CardContractDTO dto);

    @AfterMapping
    default void linkCards(@MappingTarget CardContractModel contract) {
        if (contract.getCards() != null) {
            contract.getCards().forEach(card -> card.setContract(contract));
        }
    }
}

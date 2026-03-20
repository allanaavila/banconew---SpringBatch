package com.banconew.infra.mappers;

import com.banconew.app.dto.CardDTO;
import com.banconew.domain.model.CardModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CardModelMapper {

    @Mapping(target = "contractId", source = "contract.contractId")
    CardDTO toDTO(CardModel model);

    @Mapping(target = "contract.contractId", source = "contractId")
    CardModel toModel(CardDTO dto);
}

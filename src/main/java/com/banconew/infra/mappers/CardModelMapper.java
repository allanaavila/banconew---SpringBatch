package com.banconew.infra.mappers;

import com.banconew.app.dto.CardDTO;
import com.banconew.domain.model.CardModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CardModelMapper {

    CardDTO toDTO(CardModel model);

    @Mapping(target = "contract", ignore = true)
    CardModel toModel(CardDTO dto);
}

package com.banconew.infra.mappers;

import com.banconew.app.dto.RestrictiveDTO;
import com.banconew.domain.model.RestrictiveModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CustomerModelMapper.class})
public interface RestrictiveModelMapper {

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "customerDocument", source = "customer.documentNumber")
    @Mapping(target = "occurrenceDate", source = "occurrenceDate")
    RestrictiveDTO toDTO(RestrictiveModel model);

    @Mapping(target = "customer.id", source = "customerId")
    @Mapping(target = "customer.documentNumber", source = "customerDocument")
    @Mapping(target = "occurrenceDate", source = "occurrenceDate")
    RestrictiveModel toModel(RestrictiveDTO dto);
}

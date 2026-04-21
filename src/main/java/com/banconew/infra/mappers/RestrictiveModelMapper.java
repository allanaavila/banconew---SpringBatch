package com.banconew.infra.mappers;

import com.banconew.app.dto.RestrictiveDTO;
import com.banconew.domain.model.RestrictiveModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestrictiveModelMapper {

    @Mapping(source = "customer.id", target = "customerId")
    RestrictiveDTO toDTO(RestrictiveModel model);

    @Mapping(target = "customer", ignore = true)
    RestrictiveModel toModel(RestrictiveDTO dto);
}

package com.banconew.infra.mappers;

import com.banconew.app.dto.CustomerDTO;
import com.banconew.domain.model.CustomerModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CardContractModelMapper.class})
public interface CustomerModelMapper {
    CustomerDTO toDto(CustomerModel model);
    CustomerModel toModel(CustomerDTO dto);
}

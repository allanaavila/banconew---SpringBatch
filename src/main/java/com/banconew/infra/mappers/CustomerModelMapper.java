package com.banconew.infra.mappers;

import com.banconew.app.dto.CustomerDTO;
import com.banconew.domain.model.CustomerModel;
import com.banconew.domain.model.CardContractModel;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

@Slf4j
@Mapper(componentModel = "spring", uses = {CardContractModelMapper.class})
public abstract class CustomerModelMapper {

    @Autowired
    protected CardContractModelMapper contractMapper;

    @Mapping(target = "cardContracts", ignore = true)
    public abstract CustomerModel toModel(CustomerDTO dto);

    @AfterMapping
    protected void linkContracts(CustomerDTO dto, @MappingTarget CustomerModel model) {
        if (dto.getCardContract() != null) {
            CardContractModel contractModel = contractMapper.toModel(dto.getCardContract());
            contractModel.setCustomer(model);

            model.setCardContracts(Collections.singletonList(contractModel));

            log.info("#### [MAPPER] Contrato vinculado ao Cliente: {} ####", model.getId());
        }
    }
}
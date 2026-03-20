package com.banconew.app.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private Long id;
    private String documentNumber;
    private String name;

    private List<CardContractDTO> cardContracts;
}

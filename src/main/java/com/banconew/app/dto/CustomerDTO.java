package com.banconew.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    @JsonProperty("customerId")
    private String id;

    private String name;

    private CardContractDTO cardContract;
}

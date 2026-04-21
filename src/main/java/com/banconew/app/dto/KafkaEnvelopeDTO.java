package com.banconew.app.dto;


import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Slf4j
public class KafkaEnvelopeDTO {
    private CustomerDTO customer;
}

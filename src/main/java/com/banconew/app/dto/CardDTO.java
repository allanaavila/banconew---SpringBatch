package com.banconew.app.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {
    private String cardNumber;
    private String brand;
    private String contractId;
}

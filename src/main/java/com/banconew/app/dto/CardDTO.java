package com.banconew.app.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CardDTO {
    private String cardNumber;
    private String brand;
    private BigDecimal cardLimit;
    private BigDecimal cardBalance;
    private String cardStatus;
}

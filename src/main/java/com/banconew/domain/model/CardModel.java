package com.banconew.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
@Entity
@Table(name = "card")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardModel {

    @Id
    private String cardNumber;

    private String brand;

    private BigDecimal cardLimit;

    private BigDecimal cardBalance;

    private String cardStatus;

    @ManyToOne
    @JoinColumn(name = "contract_id")
    private CardContractModel contract;
}

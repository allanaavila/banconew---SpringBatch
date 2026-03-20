package com.banconew.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import jakarta.persistence.Id;

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

    @OneToOne
    @JoinColumn(name = "contract_id")
    private CardContractModel contract;
}

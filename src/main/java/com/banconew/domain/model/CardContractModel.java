package com.banconew.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Entity
@Table(name = "card_contract")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardContractModel {
    @Id
    private String contractId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerModel customer;

    private String status;
    private LocalDateTime updateTime;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL)
    private List<CardModel> cards;
}

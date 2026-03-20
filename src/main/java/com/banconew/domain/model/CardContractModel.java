package com.banconew.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

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

    @OneToOne(mappedBy = "contract", cascade = CascadeType.ALL)
    private CardModel card;

    @Enumerated(EnumType.STRING)
    private StatusModel status;

    private LocalDateTime updateTime;
}

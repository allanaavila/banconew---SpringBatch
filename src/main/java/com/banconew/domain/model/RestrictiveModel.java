package com.banconew.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Slf4j
@Entity
@Table(name = "restrictive")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestrictiveModel {

    @Id
    private String id;

    private String name;
    private String documentNumber;
    private String email;
    private String phoneNumber;
    private String debtType;
    private String fraudType;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private CustomerModel customer;

    private LocalDate occurrenceDate;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusDescriptionModel status;
}

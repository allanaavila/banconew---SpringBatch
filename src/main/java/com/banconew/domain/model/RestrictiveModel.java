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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private String description;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerModel customer;

    private LocalDate occurrenceDate;
}

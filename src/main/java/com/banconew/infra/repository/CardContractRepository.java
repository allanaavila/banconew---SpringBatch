package com.banconew.infra.repository;

import com.banconew.domain.model.CardContractModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardContractRepository extends JpaRepository<CardContractModel, String> {
    Optional<CardContractModel> findByCustomerId(String customerId);

    boolean existsByContractId(String contractId);
}

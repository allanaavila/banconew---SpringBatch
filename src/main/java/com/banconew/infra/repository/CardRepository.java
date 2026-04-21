package com.banconew.infra.repository;
import com.banconew.domain.model.CardModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<CardModel, String>{
    Optional<CardModel> findByContractCustomerId(String customerId);
}

package com.banconew.infra.repository;

import com.banconew.domain.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, String> {
    Optional<CustomerModel> findById(String id);
}

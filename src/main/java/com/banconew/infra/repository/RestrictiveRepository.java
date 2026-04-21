package com.banconew.infra.repository;

import com.banconew.domain.model.RestrictiveModel;
import com.banconew.domain.model.StatusModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RestrictiveRepository extends JpaRepository<RestrictiveModel, String> {

    @Query("SELECT COUNT(r) > 0 FROM RestrictiveModel r WHERE r.customer.id = :customerId")
    boolean existsByCustomerId(@Param("customerId") String customerId);

    Page<RestrictiveModel> findByStatus_Id(Long statusId, Pageable pageable);

    boolean existsByCustomer_Id(String customerId);

    @Modifying
    @Query("UPDATE RestrictiveModel r SET r.status.id = :#{#status.id} WHERE r.customer.id = :customerId")
    void updateStatus(@Param("customerId") String customerId, @Param("status") StatusModel status);
}

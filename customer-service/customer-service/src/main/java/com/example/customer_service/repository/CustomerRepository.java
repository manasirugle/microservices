package com.example.customer_service.repository;

import com.example.customer_service.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByEmail(String email);

    boolean existsByLeadId(String leadId);

    @Query(value = "SELECT c FROM Customer c WHERE c.agentId =:agentId")
    List<Customer> findByAgentId(String agentId);
}

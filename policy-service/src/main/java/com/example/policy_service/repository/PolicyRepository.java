package com.example.policy_service.repository;

import com.example.policy_service.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
    List<Policy> findByCustomerId(String customerId);
    Optional<Policy> findByPolicyId(String policyId);
    boolean existsByCustomerId(String customerId);
}


package com.example.policy_service.service;

import com.example.policy_service.entity.Policy;
import com.example.policy_service.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PolicyService {

    @Autowired
    private PolicyRepository repository;

    public Policy createPolicy(Policy policy) {
        policy.setCreatedDate(LocalDate.now());
        return repository.save(policy);
    }

    public Policy getPolicyDetails(String policyId) {
        return repository.findByPolicyId(policyId)
                .orElseThrow(() -> new RuntimeException("Policy not found"));
    }

    public List<Policy> getPolicies(String customerId) {
        return repository.findByCustomerId(customerId);
    }

    public Policy updatePolicy(String policyId, Policy updatedPolicy) {
        Policy existing = getPolicyDetails(policyId);
        updatedPolicy.setId(existing.getId());
        updatedPolicy.setUpdatedDate(LocalDate.now());
        updatedPolicy.setCreatedDate(existing.getCreatedDate());
        return repository.save(updatedPolicy);
    }

    public boolean isPolicyExists(String customerId) {
        return repository.existsByCustomerId(customerId);
    }
}


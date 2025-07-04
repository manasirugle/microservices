package com.example.policy_service.service;

import com.example.policy_service.entity.Policy;
import com.example.policy_service.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class PolicyService {

    @Autowired
    private PolicyRepository repository;

    public Policy createPolicy(Policy policy) {
        policy.setCreatedDate(LocalDate.now());
        policy.setUpdatedDate(LocalDate.now());
        return repository.save(policy);
    }

    public Policy getPolicyDetails(String policyId) {
        return repository.findByPolicyId(policyId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Policy not found"));
    }
    public List<Policy> getPolicies(String customerId) {
        List<Policy> policies = repository.findByCustomerId(customerId);
        if (policies == null || policies.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No policies found for the customer");
        }
        return policies;
    }



    public Policy updatePolicy(String policyId, Policy updatedPolicy) {
        Policy existingPolicy = getPolicyDetails(policyId);
        if (existingPolicy == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Policy not found");
        }

        existingPolicy.setPolicyName(updatedPolicy.getPolicyName());
        existingPolicy.setCustomerId(updatedPolicy.getCustomerId());
        existingPolicy.setCustomerName(updatedPolicy.getCustomerName());
        existingPolicy.setUserId(updatedPolicy.getUserId());
        existingPolicy.setUserName(updatedPolicy.getUserName());
        existingPolicy.setPolicyEnforcedDate(updatedPolicy.getPolicyEnforcedDate());
        existingPolicy.setPolicyMaturityDate(updatedPolicy.getPolicyMaturityDate());
        existingPolicy.setPolicyTenure(updatedPolicy.getPolicyTenure());
        existingPolicy.setPolicySumInsured(updatedPolicy.getPolicySumInsured());
        existingPolicy.setPolicyPremium(updatedPolicy.getPolicyPremium());
        existingPolicy.setPolicyPaymentTerm(updatedPolicy.getPolicyPaymentTerm());
        existingPolicy.setIsPolicyExpired(updatedPolicy.isPolicyExpired());
        existingPolicy.setUpdatedDate(LocalDate.now());

        return repository.save(existingPolicy);
    }

    public boolean isPolicyExists(String customerId) {
        return repository.existsByCustomerId(customerId);
    }
}


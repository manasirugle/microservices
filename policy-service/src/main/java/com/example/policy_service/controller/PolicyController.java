package com.example.policy_service.controller;

import com.example.policy_service.entity.Policy;
import com.example.policy_service.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/policies")
public class PolicyController {

    @Autowired
    private PolicyService service;

    @PostMapping("/createPolicy")
    public ResponseEntity<Policy> create(@RequestBody Policy policy) {
        return ResponseEntity.ok(service.createPolicy(policy));
    }

    @GetMapping("/getPolicyDetails/{policyId}")
    public ResponseEntity<Policy> getDetails(@PathVariable String policyId) {
        return ResponseEntity.ok(service.getPolicyDetails(policyId));
    }

    @GetMapping("/getPolicies/{customerId}")
    public ResponseEntity<List<Policy>> getPolicies(@PathVariable String customerId) {
        return ResponseEntity.ok(service.getPolicies(customerId));
    }

    @PutMapping("/updatePolicy/{policyId}")
    public ResponseEntity<Policy> update(@PathVariable String policyId, @RequestBody Policy policy) {
        return ResponseEntity.ok(service.updatePolicy(policyId, policy));
    }

    @GetMapping("/isPolicyExists/{customerId}")
    public ResponseEntity<Boolean> exists(@PathVariable String customerId) {
        return ResponseEntity.ok(service.isPolicyExists(customerId));
    }
}


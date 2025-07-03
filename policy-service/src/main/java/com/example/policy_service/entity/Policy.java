package com.example.policy_service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Data
@Entity
@Table(name = "policy")
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String policyId;
    private String policyName;

    private String customerId;
    private String customerName;

    private String userId;
    private String userName;

    private LocalDate policyEnforcedDate;
    private LocalDate policyMaturityDate;

    private String policyTenure;
    private Double policySumInsured;
    private Double policyPremium;
    private String policyPaymentTerm;

    private boolean isPolicyExpired;

    private LocalDate createdDate;
    private LocalDate updatedDate;


}


package com.example.lead_service.controller;

import java.util.List;

import com.example.lead_service.dto.LeadRequest;
import com.example.lead_service.entity.Lead;
import com.example.lead_service.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leads")
public class LeadController {
    @Autowired
    private LeadService leadService;



    @Autowired
    private LeadService service;

    @PostMapping("/add")
    public ResponseEntity<String> addLead(@RequestBody Lead lead) {
        String response = leadService.addLead(lead);
        if(response.equalsIgnoreCase("Lead already exists as customer.")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(response);
    }


    @PutMapping("/update")
    public ResponseEntity<Lead> updateLead(@RequestBody Lead lead) {
        Lead updatedLead = leadService.updateLead(lead);
        System.out.println("Lead updated successfully");
        return ResponseEntity.ok(updatedLead);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLead(@PathVariable Long id) {
        String response = leadService.deleteLead(id);
        System.out.println("Lead deleted");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Lead> getLeadById(@PathVariable Long id) {
        Lead lead = leadService.findById(id);
        if (lead != null) {
            return ResponseEntity.ok(lead);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/getForAgentId/{agentId}")
    public ResponseEntity<List<Lead>> getLeadsForAgent(@PathVariable String agentId) {
        List<Lead> leads = leadService.getLeadsForAgentId(agentId);
        return ResponseEntity.ok(leads);
    }































    @PostMapping("/fetchLead")
    public Lead fetchLeadData(@RequestBody LeadRequest leadRequest) {
        System.out.println("fetchLeadData function called with request - " + leadRequest);
        return new Lead();
    }














}






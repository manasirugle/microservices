package com.example.lead_service.service;

import com.example.lead_service.entity.Lead;
import com.example.lead_service.repository.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;


@Service
public class LeadService {

    @Autowired
    private LeadRepository repo;
    @Autowired
    private LeadRepository leadRepository;


    @Autowired
    private RestTemplate restTemplate;

    public String addLead(Lead lead) {
        try {

            System.out.println("Step 1: Checking if agent ID is valid...");
            boolean agentExists = restTemplate.getForObject(
                    "http://localhost:8080/users/exists/" + lead.getAgentId().trim(),
                    Boolean.class
            );

            if (!agentExists) {
                System.out.println("Invalid agent ID.");
                return "Invalid agent ID. Lead cannot be saved.";
            }

            System.out.println("Step 2: Checking if lead is a customer...");
            boolean exists = restTemplate.getForObject(
                    "http://localhost:8082/customers/exists/" + lead.getLeadId(),
                    Boolean.class
            );

            if (exists) {
                System.out.println("Lead already exists as customer.");
                return "Lead already exists as customer.";
            }

            System.out.println("Step 3: Saving lead...");
            repo.save(lead);

            System.out.println("Lead saved successfully.");
            return "Lead saved successfully.";

        } catch (Exception e) {
            System.out.println(" Error adding lead: " + e.getMessage());
            e.printStackTrace();
            return "Exception occurred while saving lead.";
        }
    }

    @PutMapping("/update/{id}")
    public String updateLead(@PathVariable Long id, @RequestBody Lead updatedLead) {
        try {
            // Step 1: Validate agent ID
            System.out.println("Step 1: Checking if agent ID is valid...");
            boolean agentExists = restTemplate.getForObject(
                    "http://localhost:8080/users/exists/" + updatedLead.getAgentId().trim(),
                    Boolean.class
            );

            if (!agentExists) {
                System.out.println("Invalid agent ID.");
                return "Invalid agent ID. Lead cannot be updated.";
            }

            // Step 2: Check if lead is already a customer
            System.out.println("Step 2: Checking if lead is a customer...");
            boolean isCustomer = restTemplate.getForObject(
                    "http://localhost:8082/customers/exists/" + updatedLead.getLeadId(),
                    Boolean.class
            );

            if (isCustomer) {
                System.out.println("Lead already exists as customer.");
                return "Lead already exists as customer. Cannot update.";
            }

            // Step 3: Fetch lead by ID and update
            Optional<Lead> leadOptional = repo.findById(id);
            if (leadOptional.isEmpty()) {
                System.out.println("Lead not found.");
                return "Lead not found.";
            }

            Lead existingLead = leadOptional.get();
            existingLead.setName(updatedLead.getName());
            existingLead.setEmail(updatedLead.getEmail());
            existingLead.setAgentId(updatedLead.getAgentId());
            repo.save(existingLead);

            System.out.println("Lead updated successfully.");
            return "Lead updated successfully.";

        } catch (Exception e) {
            System.out.println("Error updating lead: " + e.getMessage());
            e.printStackTrace();
            return "Exception occurred while updating lead.";
        }
    }


    @DeleteMapping("/delete/{id}/{agentId}")
    public String deleteLead(@PathVariable Long id, @PathVariable String agentId) {
        try {
            // Step 1: Validate agent ID
            System.out.println("Step 1: Checking if agent ID is valid...");
            boolean agentExists = restTemplate.getForObject(
                    "http://localhost:8080/users/exists/" + agentId.trim(),
                    Boolean.class
            );

            if (!agentExists) {
                System.out.println("Invalid agent ID.");
                return "Invalid agent ID. Lead cannot be deleted.";
            }

            // Step 2: Fetch lead and check if it's a customer
            Optional<Lead> leadOptional = repo.findById(id);
            if (leadOptional.isEmpty()) {
                System.out.println("Lead not found.");
                return "Lead not found.";
            }

            Lead lead = leadOptional.get();
            System.out.println("Step 2: Checking if lead is a customer...");
            boolean isCustomer = restTemplate.getForObject(
                    "http://localhost:8082/customers/exists/" + lead.getLeadId(),
                    Boolean.class
            );

            if (isCustomer) {
                System.out.println("Lead already exists as customer.");
                return "Lead already exists as customer. Cannot delete.";
            }

            // Step 3: Delete the lead
            repo.deleteById(id);
            System.out.println("Lead deleted successfully.");
            return "Lead deleted successfully.";

        } catch (Exception e) {
            System.out.println("Error deleting lead: " + e.getMessage());
            e.printStackTrace();
            return "Exception occurred while deleting lead.";
        }
    }


    public Lead updateLead(Lead lead) {
        return repo.save(lead);
    }

    public String deleteLead(Long id) {
        repo.deleteById(id);
        return "Lead deleted";

    }

    public Lead findById(Long id) {
        return leadRepository.findById(id).orElse(null);
    }

    public List<Lead> getLeadsForAgentId(String agentId) {
        return leadRepository.findByAgentId(agentId);
    }


}

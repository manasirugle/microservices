package com.example.customer_service.controller;

import com.example.customer_service.entity.Customer;
import com.example.customer_service.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;


    @GetMapping("/exists/{leadId}")
    public ResponseEntity<Boolean> isExistingCustomer(@PathVariable String leadId) {
        System.out.println("Looking for existing customers...");
        boolean exists = service.isExistingCustomer(leadId);
        return ResponseEntity.ok(exists);
    }

    @PostMapping("/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        System.out.println("Customer received: " + customer);
        Customer savedCustomer = service.addCustomer(customer);
        System.out.println("Customer saved to DB with ID: " + savedCustomer.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
    }

    @PutMapping("/update")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        Customer updatedCustomer = service.updateCustomer(customer);
        System.out.println("Customer updated");
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        String response = service.deleteCustomer(id);
        System.out.println("Customer deleted");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getForAgentId/{agentId}")
    public ResponseEntity<List<Customer>> getCustomersForAgentId(@PathVariable String agentId) {
        List<Customer> customers = service.getCustomerForAgentId(agentId);
        return ResponseEntity.ok(customers);
    }

}

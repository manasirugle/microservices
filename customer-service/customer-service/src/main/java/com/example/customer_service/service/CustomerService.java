package com.example.customer_service.service;

import com.example.customer_service.entity.Customer;
import com.example.customer_service.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repo;

    public boolean isExistingCustomer(String leadId) {
//        return repo.existsByEmail(email);
        return repo.existsByLeadId(leadId);
    }

    public Customer addCustomer(Customer customer) {
        return repo.save(customer);
    }

    public Customer updateCustomer(Customer customer) {
        return repo.save(customer);
    }

    public String deleteCustomer(Long id) {
        repo.deleteById(id);
        return "Customer deleted";
    }


    public List<Customer> getCustomerForAgentId(String agentId) {
        return repo.findByAgentId(agentId);
    }


}


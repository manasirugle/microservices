package com.example.user_service.service;

import com.example.user_service.entity.User;
import com.example.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User addUser(User user) {
        return repo.save(user); // No encoding here
    }


    public List<User> getAllUsers() {
        return repo.findAll();
    }


    public User findByEmail(String email) {
        List<User> users = repo.findByEmail(email);
        if (!users.isEmpty()) {
            return users.get(0); // just take the first one
        }
        return null;
    }


    public boolean validateUser(String username, String password) {
        List<User> users = repo.findByUsername(username);

        if (users.isEmpty()) {
            return false;
        }

        for (User user : users) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return true;
            }
        }

        return false;
    }


    public boolean isExistingUser(String agentId) {
        try {
            return repo.existsByAgentId(agentId);
        } catch (Exception e) {
            System.out.println("Error checking agentId: " + e.getMessage());
            return false;
        }
    }
}





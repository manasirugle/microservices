package com.example.user_service.controller;//package com.example.user_service.controller;

import com.example.user_service.dto.LoginRequest;
import com.example.user_service.entity.User;
import com.example.user_service.repository.UserRepository;
import com.example.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        System.out.println("Adding user: " + user.getUsername());
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return service.addUser(user);
    }


    @PostMapping("/validate")
    public String validate(@RequestBody LoginRequest request) {
        boolean valid = service.validateUser(request.getUsername(), request.getPassword());
        return valid ? "User is valid" : "Invalid username or password";
    }


    @GetMapping("/exists/{agentId}")
    public boolean checkIfAgentExists(@PathVariable String agentId) {
        return service.isExistingUser(agentId);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }


    @GetMapping("/hello")
    public String hello() {
        return "Hello from User Service!";
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
        try {
            System.out.println("Attempting login for: " + request.getEmail());

            List<User> users = userRepository.findByEmail(request.getEmail());

            if (users.isEmpty()) {
                System.out.println("User not found.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
            }

            for (User user : users) {
                if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                    System.out.println("Login successful for: " + user.getUsername());
                    return ResponseEntity.ok("Login successful.");
                }
            }

            System.out.println("Incorrect password.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");

        } catch (Exception e) {
            System.out.println("Login error: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login failed due to server error.");
        }
    }


}


















//    @PostMapping("/login")
//    public ResponseEntity<Map<String, String>> validateUser1(@RequestBody LoginRequest loginRequest) {
//        String encryptedPassword = encryptionService.getEncryptedString(loginRequest.getPassword());
//        boolean isValid = service.validateUser(loginRequest.getUsername(), encryptedPassword);
//
//        Map<String, String> response = new HashMap<>();
//        if (isValid) {
//            response.put("message", "Login successful");
//            return ResponseEntity.ok(response); // HTTP 200
//        } else {
//            response.put("message", "Invalid credentials");
//            return ResponseEntity.status(401).body(response); // HTTP 401 Unauthorized
//        }
//    }







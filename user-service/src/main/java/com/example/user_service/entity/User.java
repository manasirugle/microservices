package com.example.user_service.entity;

import jakarta.persistence.*;
import lombok.Data;



@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String agentId;
    private String username;
    private String email;
    private String password;

}




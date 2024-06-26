package com.restaurant.system.backend_restaurant_system.persistence.entity;

import com.restaurant.system.backend_restaurant_system.persistence.util.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "db_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;
    
    private String password;

    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;
    
}

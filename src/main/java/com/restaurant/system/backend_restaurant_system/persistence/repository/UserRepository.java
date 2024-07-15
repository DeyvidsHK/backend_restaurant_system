package com.restaurant.system.backend_restaurant_system.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.restaurant.system.backend_restaurant_system.persistence.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(String email);

}

package com.restaurant.system.backend_restaurant_system.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.system.backend_restaurant_system.persistence.entity.ServiceTable;

public interface ServiceTableRepository extends JpaRepository<ServiceTable, Long>{
    
    Optional<List<ServiceTable>> findByRoomId(Long roomId);

}

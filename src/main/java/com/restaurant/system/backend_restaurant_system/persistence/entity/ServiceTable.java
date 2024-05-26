package com.restaurant.system.backend_restaurant_system.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "db_table")
@Data
public class ServiceTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "chair_count")
    private Integer chairCount;

    @Column(name = "room_id")
    private Integer roomId;

    @Column(name = "is_occupied")
    private Boolean isOccupied;
    
}

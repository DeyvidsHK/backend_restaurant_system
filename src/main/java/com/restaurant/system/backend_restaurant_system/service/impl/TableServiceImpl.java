package com.restaurant.system.backend_restaurant_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.system.backend_restaurant_system.dto.ServiceTableDTO;
import com.restaurant.system.backend_restaurant_system.persistence.entity.ServiceTable;
import com.restaurant.system.backend_restaurant_system.persistence.repository.ServiceTableRepository;
import com.restaurant.system.backend_restaurant_system.service.TableService;
import java.util.List;
import java.util.Optional;

@Service
public class TableServiceImpl implements TableService{
    
    @Autowired
    private ServiceTableRepository serviceTableRepository;

    @Override
    public ServiceTableDTO getTableByIdRoom(Long id) {
        Optional<List<ServiceTable>> tables = serviceTableRepository.findByRoomId(id);
        if (tables.isEmpty() || tables.get().isEmpty()) {
            return new ServiceTableDTO("No se encontraron mesas asociadas al Piso", 403, null);
        } else {
            return new ServiceTableDTO("", 200, tables.get());
        }
    }

}

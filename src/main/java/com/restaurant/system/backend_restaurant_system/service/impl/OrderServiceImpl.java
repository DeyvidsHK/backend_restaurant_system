package com.restaurant.system.backend_restaurant_system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.restaurant.system.backend_restaurant_system.dto.CreateOrderDTO;
import com.restaurant.system.backend_restaurant_system.dto.CreateOrderDetailDTO;
import com.restaurant.system.backend_restaurant_system.dto.MessageDTO;
import com.restaurant.system.backend_restaurant_system.dto.OrderDTO;
import com.restaurant.system.backend_restaurant_system.dto.OrderDetailDTO;
import com.restaurant.system.backend_restaurant_system.dto.pagination.OrderPaginationDTO;
import com.restaurant.system.backend_restaurant_system.persistence.entity.Dish;
import com.restaurant.system.backend_restaurant_system.persistence.entity.Order;
import com.restaurant.system.backend_restaurant_system.persistence.entity.OrderDetail;
import com.restaurant.system.backend_restaurant_system.persistence.entity.User;
import com.restaurant.system.backend_restaurant_system.persistence.repository.DishRepository;
import com.restaurant.system.backend_restaurant_system.persistence.repository.OrderDetailRepository;
import com.restaurant.system.backend_restaurant_system.persistence.repository.OrderRepository;
import com.restaurant.system.backend_restaurant_system.persistence.repository.UserRepository;
import com.restaurant.system.backend_restaurant_system.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DishRepository dishRepository;

    @Override
    public OrderPaginationDTO getAllOrder(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);

        Page<Order> orderPage = orderRepository.findAll(pageable);

        List<OrderDTO> orderDTOList = orderPage.getContent().stream()
                .map(order -> mapOrderToDTO(order))
                .collect(Collectors.toList());

        return new OrderPaginationDTO(orderPage.getTotalPages(), orderPage.getTotalElements(), orderPage.getSize(), orderDTOList);
    }

    public OrderDTO mapOrderToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(order.getId());

        orderDTO.setId(order.getId());
        orderDTO.setWaiter(order.getUser().getName());
        
        List<OrderDetailDTO> orderDetailDTOs = orderDetails.stream()
            .map(orderDetail -> mapOrderDetailToDTO(orderDetail))
            .collect(Collectors.toList());

        orderDTO.setOrderDetails(orderDetailDTOs);
        return orderDTO;
    }

    private OrderDetailDTO mapOrderDetailToDTO(OrderDetail orderDetail) {
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        orderDetailDTO.setQuantity(orderDetail.getQuantity());
        orderDetailDTO.setDish(orderDetail.getDish().getName());
        orderDetailDTO.setPrice(orderDetail.getDish().getPrice());
        
        return orderDetailDTO;
    }

    @Override
    public MessageDTO createOrder(CreateOrderDTO createOrderDTO) {
        MessageDTO message = new MessageDTO();
        
        // Crear una nueva instancia de Order
        Order order = new Order();

        // Buscar el usuario por ID
        Optional<User> user = userRepository.findById(createOrderDTO.getUser_id());

        if (!user.isPresent()) {
            message.setStatus(404);
            message.setMessage("Usuario no encontrado");
            return message;
        }

        // Asignar el usuario y la fecha de la orden
        order.setUser(user.get());
        order.setOrderDate(new Date());

        // Guardar la orden en la base de datos para generar el ID
        Order savedOrder = orderRepository.save(order);

        // Lista para almacenar los detalles de la orden
        List<OrderDetail> orderDetails = new ArrayList<>();

        // Variable de control para manejar el estado de error
        boolean platoNoEncontrado = false;

        // Iterar sobre los detalles de la orden y guardarlos
        for (CreateOrderDetailDTO orderDetailDTO : createOrderDTO.getOrderDetail()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(savedOrder);
            
            // Buscar el plato por ID
            Optional<Dish> optionalDish = dishRepository.findById(orderDetailDTO.getDish_id());
            
            if (optionalDish.isPresent()) {
                orderDetail.setDish(optionalDish.get());
                orderDetail.setQuantity(orderDetailDTO.getQuantity());
                orderDetails.add(orderDetailRepository.save(orderDetail));
            } else {
                // Configurar el mensaje de error si el plato no es encontrado
                message.setStatus(404);
                message.setMessage("Plato no encontrado con el ID:" + orderDetailDTO.getDish_id());
                platoNoEncontrado = true; // Marcamos que hay un error de plato no encontrado
                break; // Salimos del bucle foreach
            }
        }

        // Verificar si ocurrió un error de plato no encontrado
        if (platoNoEncontrado) {
            return message; // Retornar el mensaje de error
        }

        // Configurar el mensaje de respuesta
        message.setStatus(201); // Esto puede variar dependiendo de la lógica de tu aplicación
        message.setMessage("Orden creada exitosamente");
        return message;
    }


    
}

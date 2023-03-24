package com.example.demo.service.impl;

import com.example.demo.model.dto.request.OrderRequest;
import com.example.demo.model.dto.response.OrderResponse;
import com.example.demo.model.entity.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.FirebaseService;
import com.example.demo.service.OrderLineService;
import com.example.demo.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderLineService orderLineService;
    private final ModelMapper modelMapper;
    private final FirebaseService firebaseService;

    @Override
    @Transactional
    public OrderResponse saveOrder(OrderRequest newOrder, String userId) {
        Order order = modelMapper.map(newOrder, Order.class);

        if (firebaseService.checkUserExists(userId)) {
            order.setIdUser(userId);
            Order savedOrder = orderRepository.save(order);
            orderLineService.saveAllOrderLines(savedOrder.getId(), newOrder.getOrderLines());
            return modelMapper.map(order, OrderResponse.class);
        }
        return null;
    }


    @Override
    public OrderResponse getOrder(Long orderId, String userId) {
        Optional<Order> dbOrder = orderRepository.findByIdAndIdUser(orderId, userId);
        if (firebaseService.checkUserExists(userId) && dbOrder.isPresent()) {
            return modelMapper.map(dbOrder, OrderResponse.class);
        }
        return null;
    }

    @Override
    public List<OrderResponse> getAllOrder(String userId) {
        if (firebaseService.checkUserExists(userId)) {
            List<Order> ordersByUser = orderRepository.getAllByIdUser(userId);

            return ordersByUser.stream()
                    .map(order -> modelMapper.map(order, OrderResponse.class))
                    .toList();
        }
        return null;
    }

    @Override
    public Long getTotalOrderPrice(Long orderId, String userId) {
        return orderRepository.getTotalOrderPrice(orderId, userId);
    }

}

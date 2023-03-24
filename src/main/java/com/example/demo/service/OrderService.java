package com.example.demo.service;

import com.example.demo.model.dto.request.OrderRequest;
import com.example.demo.model.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse saveOrder(OrderRequest newOrder, String userId);

    OrderResponse getOrder(Long orderId, String userId);

    List<OrderResponse> getAllOrder(String userId);

    Long getTotalOrderPrice(Long orderId, String userId);


}

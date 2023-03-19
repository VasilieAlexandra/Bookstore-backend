package com.example.demo.service;

import com.example.demo.model.dto.request.OrderLineRequest;
import com.example.demo.model.dto.response.OrderLineResponse;

import java.util.List;
import java.util.Set;

public interface OrderLineService {
//    OrderLineResponse addOrderLine(OrderLineRequest newOrderLine, String userId, Long orderId);
//    OrderLineResponse updateOrderLine(OrderLineRequest newOrderLine, String userId, Long orderId);
//    OrderLineResponse deleteOrderLine(OrderLineRequest newOrderLine);
    OrderLineResponse getOrderLine(Long bookId, String userId, Long orderId);
    List<OrderLineResponse> getAllOrderLines(String userId, Long orderId);
    List<OrderLineResponse> saveAllOrderLines(Long orderId, Set<OrderLineRequest> orderLines);
}

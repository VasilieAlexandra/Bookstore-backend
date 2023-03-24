package com.example.demo.service;

import com.example.demo.model.dto.request.OrderLineRequest;
import com.example.demo.model.dto.response.OrderLineResponse;

import java.util.List;
import java.util.Set;

public interface OrderLineService {
    OrderLineResponse getOrderLine(Long bookId, String userId, Long orderId);
    List<OrderLineResponse> getAllOrderLines(String userId, Long orderId);
    void saveAllOrderLines(Long orderId, Set<OrderLineRequest> orderLines);
}

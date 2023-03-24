package com.example.demo.controller;

import com.example.demo.model.dto.request.OrderRequest;
import com.example.demo.model.dto.response.OrderResponse;
import com.example.demo.service.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{user_id}/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> addOrder(@PathVariable(name = "user_id") String id,
                                                  @Valid @RequestBody OrderRequest orderRequest) {


        OrderResponse orderResponse = orderService.saveOrder(orderRequest, id);
        if (orderResponse != null)
            return new ResponseEntity<>(orderResponse, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{order_id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable(name = "user_id") String id,
                                                  @PathVariable(name = "order_id") Long orderId) {
        OrderResponse orderResponse = orderService.getOrder(orderId, id);

        if (orderResponse != null) {
            return new ResponseEntity<>(orderResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders(@PathVariable(name = "user_id") String id) {

        List<OrderResponse> orderResponse = orderService.getAllOrder(id);
        if (orderResponse != null) {
            return new ResponseEntity<>(orderResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{order_id}/price")
    public ResponseEntity<Long> getTotalOrderPrice(@PathVariable(name = "user_id") String id, @PathVariable(name = "order_id") Long orderId) {

        Long price = orderService.getTotalOrderPrice(orderId, id);
        if (price != null) {
            return new ResponseEntity<>(price, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

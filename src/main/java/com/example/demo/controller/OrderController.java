package com.example.demo.controller;

import com.example.demo.model.dto.request.OrderRequest;
import com.example.demo.model.dto.response.OrderResponse;
import com.example.demo.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users/{user_id}/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> addUserBook(@PathVariable(name = "user_id") String id,
                                                     @Valid @RequestBody OrderRequest orderRequest) {


        OrderResponse orderResponse = orderService.saveOrder(orderRequest,id);
        if (orderResponse != null)
            return new ResponseEntity<>(orderResponse, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{order_id}")
    public ResponseEntity<OrderResponse> getTaskForUser(@PathVariable(name = "user_id") String id,
                                                             @PathVariable(name = "order_id") Long orderId) {
        OrderResponse orderResponse = orderService.getOrder(orderId,id);

        if (orderResponse != null) {
            return new ResponseEntity<>(orderResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllTasksForUser(@PathVariable(name = "user_id") String id) {

        List<OrderResponse> orderResponse = orderService.getAllOrder(id);
        if (orderResponse != null) {
            return new ResponseEntity<>(orderResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

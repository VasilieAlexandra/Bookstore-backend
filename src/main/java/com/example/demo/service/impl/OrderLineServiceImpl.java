package com.example.demo.service.impl;

import com.example.demo.model.dto.request.OrderLineRequest;
import com.example.demo.model.dto.response.OrderLineResponse;
import com.example.demo.model.entity.Order;
import com.example.demo.model.entity.OrderLine;
import com.example.demo.model.entity.OrderLinePK;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.OrderLineRepository;
import com.example.demo.service.FirebaseService;
import com.example.demo.service.OrderLineService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class OrderLineServiceImpl implements OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final ModelMapper modelMapper;
    private final FirebaseService firebaseService;
    private final BookRepository bookRepository;


    @Override
    public OrderLineResponse getOrderLine(Long bookId, String userId, Long orderId) {
        if (firebaseService.checkUserExists(userId)) {
            Optional<OrderLine> dbOrderLine = orderLineRepository.findById(new OrderLinePK(orderId, bookId));
            return dbOrderLine
                    .map(orderLine -> modelMapper.map(orderLine, OrderLineResponse.class))
                    .orElse(null);
        }
        return null;
    }

    @Override
    public List<OrderLineResponse> getAllOrderLines(String userId, Long orderId) {
        if (firebaseService.checkUserExists(userId)) {
            return orderLineRepository.getAllByIdOrder(orderId).stream()
                    .map(orderLine -> modelMapper.map(orderLine, OrderLineResponse.class))
                    .toList();
        }
        return null;
    }

    @Override
    @Transactional
    public void saveAllOrderLines(Long orderId, Set<OrderLineRequest> orderLines) {

        List<OrderLine> dbOrderLines = orderLines.stream()
                .map(orderLineRequest -> modelMapper.map(orderLineRequest, OrderLine.class))
                .peek(orderLine -> orderLine.setIdOrder(orderId))
                .peek(orderLine -> orderLine.setOrder(new Order(orderId, null, null, null, null, null)))
                .toList();

        for (OrderLine line : dbOrderLines) {
            bookRepository.updateQuantity(line.getIdBook(), line.getQuantity());
        }


        orderLineRepository.saveAll(dbOrderLines);

        dbOrderLines.stream()
                .map(line -> modelMapper.map(line, OrderLineResponse.class))
                .toList();
    }
}

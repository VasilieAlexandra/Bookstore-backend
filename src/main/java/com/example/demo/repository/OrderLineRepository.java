package com.example.demo.repository;

import com.example.demo.model.entity.OrderLine;
import com.example.demo.model.entity.OrderLinePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, OrderLinePK> {
    List<OrderLine> getAllByIdOrder(Long orderId);
}

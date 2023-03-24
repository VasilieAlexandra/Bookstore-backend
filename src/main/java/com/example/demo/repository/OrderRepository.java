package com.example.demo.repository;

import com.example.demo.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByIdAndIdUser(Long orderId, String userId);

    List<Order> getAllByIdUser(String userId);

    @Query("""
            SELECT sum(l.quantity * b.price) FROM Order as o\s
            inner join OrderLine as l On o.id=l.idOrder\s
            inner join Book as b on b.id=l.idBook where o.id=(:id) and o.idUser=(:userId)""")
    Long getTotalOrderPrice(@Param("id") Long idOrder, @Param("userId") String userId);

}

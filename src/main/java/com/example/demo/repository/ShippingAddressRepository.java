package com.example.demo.repository;

import com.example.demo.model.entity.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShippingAddressRepository extends JpaRepository<ShippingAddress,Long> {
    Optional<ShippingAddress> findByIdAndIdUser(Long id, String userId);
    List<ShippingAddress> getAllByIdUser(String userId);
}

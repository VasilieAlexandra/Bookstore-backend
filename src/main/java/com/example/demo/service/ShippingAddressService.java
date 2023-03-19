package com.example.demo.service;

import com.example.demo.model.dto.ShippingAddressDto;

import java.util.List;

public interface ShippingAddressService {

    ShippingAddressDto saveAddress(String userId, ShippingAddressDto newAddress);
    ShippingAddressDto updateAddress(String userId, ShippingAddressDto updatedAddress);
    Boolean deleteAddress(String userId, Long addressId);
    ShippingAddressDto getAddressById(String userId, Long addressId);
    List<ShippingAddressDto> getAllAddressForUser(String userId);

}

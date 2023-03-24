package com.example.demo.service.impl;

import com.example.demo.model.dto.ShippingAddressDto;
import com.example.demo.model.entity.ShippingAddress;
import com.example.demo.repository.ShippingAddressRepository;
import com.example.demo.service.FirebaseService;
import com.example.demo.service.ShippingAddressService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class ShippingAddressServiceImpl implements ShippingAddressService {

    private final ShippingAddressRepository shippingAddressRepository;
    private final ModelMapper modelMapper;
    private final FirebaseService firebaseService;

    @Override
    public ShippingAddressDto saveAddress(String userId, ShippingAddressDto newAddress) {
        ShippingAddress shippingAddress = modelMapper.map(newAddress, ShippingAddress.class);

        Logger logger = Logger.getLogger(this.getClass().getName());
        logger.warning(userId);

        if (userId != null && firebaseService.checkUserExists(userId)) {
            shippingAddress.setIdUser(userId);
            shippingAddressRepository.save(shippingAddress);
            return modelMapper.map(shippingAddress, ShippingAddressDto.class);
        }
        return null;
    }

    @Override
    public ShippingAddressDto updateAddress(String userId, ShippingAddressDto updatedAddress) {
        if (firebaseService.checkUserExists(userId)) {
            Optional<ShippingAddress> dbAddress = shippingAddressRepository.findByIdAndIdUser(updatedAddress.getId(), userId);
            if (dbAddress.isPresent()) {
                ShippingAddress savedAddress = dbAddress.get();
                savedAddress.setAddress(updatedAddress.getAddress());
                savedAddress.setCity(updatedAddress.getCity());
                savedAddress.setCountry(updatedAddress.getCountry());
                savedAddress.setState(updatedAddress.getState());
                shippingAddressRepository.save(savedAddress);
                return modelMapper.map(savedAddress, ShippingAddressDto.class);
            }
        }
        return null;
    }

    @Override
    public Boolean deleteAddress(String userId, Long addressId) {
        if (firebaseService.checkUserExists(userId)) {
            Optional<ShippingAddress> dbAddress = shippingAddressRepository.findByIdAndIdUser(addressId, userId);
            if (dbAddress.isPresent()) {
                shippingAddressRepository.delete(dbAddress.get());
                return true;
            }
        }
        return false;
    }

    @Override
    public ShippingAddressDto getAddressById(String userId, Long addressId) {
        if (firebaseService.checkUserExists(userId)) {
            Optional<ShippingAddress> dbAddress = shippingAddressRepository.findByIdAndIdUser(addressId, userId);
            return dbAddress
                    .map(shippingAddress -> modelMapper.map(shippingAddress, ShippingAddressDto.class))
                    .orElse(null);
        }
        return null;
    }

    @Override
    public List<ShippingAddressDto> getAllAddressForUser(String userId) {
        if (firebaseService.checkUserExists(userId)) {
            return shippingAddressRepository.getAllByIdUser(userId).stream()
                    .map(shippingAddress -> this.modelMapper.map(shippingAddress, ShippingAddressDto.class))
                    .toList();
        }
        return null;
    }

}

package com.example.demo.controller;

import com.example.demo.model.dto.ShippingAddressDto;
import com.example.demo.service.ShippingAddressService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{user_id}/addresses")

public class AddressController {

    private final ShippingAddressService shippingAddressService;

    public AddressController(ShippingAddressService shippingAddressService) {
        this.shippingAddressService = shippingAddressService;
    }

    @PostMapping
    public ResponseEntity<ShippingAddressDto> addUserBook(@PathVariable(name = "user_id") String id,
                                                          @Valid @RequestBody ShippingAddressDto newAddress) {


        ShippingAddressDto shippingAddressDto = shippingAddressService.saveAddress(id, newAddress);
        if (shippingAddressDto != null)
            return new ResponseEntity<>(shippingAddressDto, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    public ResponseEntity<ShippingAddressDto> updateUserBook(@PathVariable(name = "user_id") String id,
                                                             @Valid @RequestBody ShippingAddressDto addressRequest) {


        ShippingAddressDto addressDto = shippingAddressService.updateAddress(id, addressRequest);
        if (addressDto != null)
            return new ResponseEntity<>(addressDto, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{address_id}")
    public ResponseEntity<Boolean> deleteUserBook(@PathVariable(name = "user_id") String id,
                                                  @PathVariable(name = "address_id") Long addressId) {

        if (shippingAddressService.deleteAddress(id, addressId))
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{address_id}")
    public ResponseEntity<ShippingAddressDto> getTaskForUser(@PathVariable(name = "user_id") String id,
                                                             @PathVariable(name = "address_id") Long addressId) {
        ShippingAddressDto addressDto = shippingAddressService.getAddressById(id, addressId);

        if (addressDto != null) {
            return new ResponseEntity<>(addressDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping
    public ResponseEntity<List<ShippingAddressDto>> getAllTasksForUser(@PathVariable(name = "user_id") String id) {

        List<ShippingAddressDto> addresses = shippingAddressService.getAllAddressForUser(id);
        if (addresses != null) {
            return new ResponseEntity<>(addresses, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

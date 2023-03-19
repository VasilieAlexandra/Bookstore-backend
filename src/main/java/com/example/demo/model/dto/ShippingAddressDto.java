package com.example.demo.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ShippingAddressDto {
    private Long id;
    private String country;
    private String state;
    private String city;
    private String address;
}

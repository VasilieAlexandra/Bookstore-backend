package com.example.demo.model.dto.response;

import com.example.demo.model.dto.ShippingAddressDto;
import lombok.*;

import java.sql.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class OrderResponse {
    private Long id;
    private Date date;
    private ShippingAddressDto shippingAddressByIdAddress;
    private Set<OrderLineResponse> orderLinesById;
}

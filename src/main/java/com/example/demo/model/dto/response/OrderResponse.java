package com.example.demo.model.dto.response;

import com.example.demo.model.dto.ShippingAddressDto;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;
    private ShippingAddressDto shippingAddress;
    private Set<OrderLineResponse> orderLines;
}

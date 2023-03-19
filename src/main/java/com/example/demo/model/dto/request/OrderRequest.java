package com.example.demo.model.dto.request;

import lombok.*;

import java.sql.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class OrderRequest {
    private Long id;
    private Date date;
    private Long idAddress;
    private Set<OrderLineRequest> orderLinesById;
}

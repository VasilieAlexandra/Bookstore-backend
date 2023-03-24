package com.example.demo.model.dto.request;

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
public class OrderRequest {
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private Long idAddress;

    private Set<OrderLineRequest> orderLines;
}

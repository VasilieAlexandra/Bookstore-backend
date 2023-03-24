package com.example.demo.model.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class OrderLineRequest {
    private Long idBook;
    private Long quantity;
}

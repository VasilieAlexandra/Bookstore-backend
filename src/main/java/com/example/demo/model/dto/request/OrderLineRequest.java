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

    //cantitatea trb sa fie mai mic egala decat cantitatea stoc carte,
    //dupa plasarea comenzii trb update la carte(quantity in db)
    private Long quantity;
}

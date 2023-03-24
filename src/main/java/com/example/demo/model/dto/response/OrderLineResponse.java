package com.example.demo.model.dto.response;

import com.example.demo.model.dto.BookDto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class OrderLineResponse {
    private BookDto book;
    private Long quantity;
}

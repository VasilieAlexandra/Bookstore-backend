package com.example.demo.model.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BookResponse {

    private Long id;
    private String name;
    private String author;
    private byte[] image;
    private Double price;
    private String sellerName;
    private Long quantity;
}

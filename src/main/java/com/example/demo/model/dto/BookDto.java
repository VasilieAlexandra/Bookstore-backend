package com.example.demo.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BookDto {
    private String name;
    private String author;
    @ToString.Exclude
    private byte[] image;
    private Long price;
}

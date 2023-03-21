package com.example.demo.model.dto.response;

import com.example.demo.model.dto.CategoryDto;
import lombok.*;

import java.util.List;
import java.util.Set;

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
    @ToString.Exclude
    private byte[] image;
    private Double price;
    private Long quantity;
    private Set<CategoryDto> categories;
}

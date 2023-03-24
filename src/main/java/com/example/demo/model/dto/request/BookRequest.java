package com.example.demo.model.dto.request;

import com.example.demo.model.entity.Category;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BookRequest {
    private Long id;
    private String name;
    private String author;
    @ToString.Exclude
    private byte[] image;
    private Long price;
    private Long quantity;
    private Set<Category> categories;
}

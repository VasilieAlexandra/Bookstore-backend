package com.example.demo.model.dto.request;

import com.example.demo.model.entity.Category;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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
    private Double price;
    private Long quantity;
    private Set<Category> categories;
}

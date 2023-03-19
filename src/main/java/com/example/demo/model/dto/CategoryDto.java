package com.example.demo.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CategoryDto {
    private Long id;
    private String name;
}

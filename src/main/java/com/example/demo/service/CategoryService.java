package com.example.demo.service;

import com.example.demo.model.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto getCategoryById(Long id);

    List<CategoryDto> getAllCategories();
}

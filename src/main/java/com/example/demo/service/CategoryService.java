package com.example.demo.service;

import com.example.demo.model.dto.CategoryDto;
import com.example.demo.model.entity.Category;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryService {

    CategoryDto getCategoryById(Long id);

    List<CategoryDto> getAllCategories();
    List<CategoryDto> getCategoriesNotInBook(Long bookId, List<Long> categories);
}

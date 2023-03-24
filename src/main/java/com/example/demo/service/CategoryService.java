package com.example.demo.service;

import com.example.demo.model.dto.CategoryDto;
import com.example.demo.model.dto.response.BookResponse;

import java.util.List;

public interface CategoryService {

    CategoryDto getCategoryById(Long id);

    List<BookResponse> getBooksByCategory(Long id, String idUser);

    List<CategoryDto> getAllCategories();

    List<CategoryDto> getCategoriesNotInBook(Long bookId, List<Long> categories);
}

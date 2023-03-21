package com.example.demo.service.impl;

import com.example.demo.model.dto.CategoryDto;
import com.example.demo.model.entity.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto getCategoryById(Long id) {

        Optional<Category> categoryDb = categoryRepository.findById(id);
        return categoryDb
                .map(c -> this.modelMapper.map(c, CategoryDto.class))
                .orElse(null);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(c-> this.modelMapper.map(c, CategoryDto.class))
                .toList();
    }

    @Override
    public List<CategoryDto> getCategoriesNotInBook(Long bookId, List<Long> categories) {
        return categoryRepository.getCategoriesNotInBook(bookId, categories).stream()
                .map(c-> this.modelMapper.map(c, CategoryDto.class))
                .toList();
    }
}

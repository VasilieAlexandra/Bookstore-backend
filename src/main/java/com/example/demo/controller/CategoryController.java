package com.example.demo.controller;

import com.example.demo.model.dto.CategoryDto;
import com.example.demo.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")

public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{category_id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable(name = "category_id") Long id) {

        CategoryDto categoryDto = categoryService.getCategoryById(id);
        if (categoryDto != null)
            return new ResponseEntity<>(categoryDto, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {

        List<CategoryDto> categoryDtoList = categoryService.getAllCategories();
        if (categoryDtoList != null)
            return new ResponseEntity<>(categoryDtoList, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

package com.example.demo.service;

import com.example.demo.model.dto.request.BookRequest;
import com.example.demo.model.dto.response.BookResponse;
import com.example.demo.model.entity.BookOrderEnum;
import com.example.demo.model.entity.Category;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BookService {
    BookResponse addBook(BookRequest newBook, MultipartFile multipartFile, String sellerId) throws IOException;
    BookResponse getBookById(Long bookId);
    BookResponse updateBook(Long bookId, BookRequest updatedBook, MultipartFile multipartFile, String sellerId) throws IOException;
    boolean deleteBook(Long bookId, String sellerId);

    List<BookResponse> findAll(String idSeller, List<String> author, List<Category> category,
                               Double minPrice, Double maxPrice, BookOrderEnum order);
}

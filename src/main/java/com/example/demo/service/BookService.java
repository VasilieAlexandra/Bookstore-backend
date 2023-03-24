package com.example.demo.service;

import com.example.demo.model.dto.response.BookResponse;
import org.springframework.data.repository.query.Param;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BookService {
    BookResponse addBook(String bookRequest, MultipartFile multipartFile, String sellerId) throws IOException;

    BookResponse getBookById(Long bookId);

    BookResponse updateBook(String bookRequest, MultipartFile multipartFile, String sellerId) throws IOException;

    boolean deleteBook(Long bookId, String sellerId);

    List<BookResponse> getAvailableBooks(String idSeller, String category,
                                         String minPrice, String maxPrice);

    List<BookResponse> getBooksAuthorOrTitle(@Param("str") String sequence, String idUser);

    List<BookResponse> getAllByIdOwner(String idUser);

}

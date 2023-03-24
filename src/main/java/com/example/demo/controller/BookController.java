package com.example.demo.controller;

import com.example.demo.model.dto.response.BookResponse;
import com.example.demo.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")

public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{book_id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable(name = "book_id") Long bookId) {
        BookResponse bookResponse = bookService.getBookById(bookId);

        if (bookResponse != null) {
            return new ResponseEntity<>(bookResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooksByFilters(@RequestParam(required = false, name = "id", defaultValue = "") String idSeller,
                                                                   @RequestParam(name = "categories", required = false, defaultValue = "") String categories,
                                                                   @RequestParam(name = "min", required = false) String minPrice,
                                                                   @RequestParam(name = "max", required = false) String maxPrice) {

        List<BookResponse> books = bookService.getAvailableBooks(idSeller, categories, minPrice, maxPrice);
        if (books != null) {
            return new ResponseEntity<>(books, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookResponse>> getAllBooksForSearch(@RequestParam(name = "str") String str,
                                                                   @RequestParam(name = "id", required = false, defaultValue = "") String idUser) {

        List<BookResponse> books = bookService.getBooksAuthorOrTitle(str, idUser);
        if (books != null) {
            return new ResponseEntity<>(books, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}


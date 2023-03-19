package com.example.demo.controller;

import com.example.demo.model.dto.response.BookResponse;
import com.example.demo.model.entity.BookOrderEnum;
import com.example.demo.model.entity.Category;
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
    public ResponseEntity<BookResponse> getTaskForUser(@PathVariable(name="book_id") Long bookId){
        BookResponse bookResponse = bookService.getBookById(bookId);

        if(bookResponse != null) {
            return new ResponseEntity<>(bookResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllTasksForUser(@RequestParam(required = false) String idSeller,
                                                                 @RequestParam(required = false, defaultValue = "[]") List<String> author,
                                                                 @RequestParam(required = false, defaultValue = "[]") List<Category> category,
                                                                 @RequestParam(required = false) Double minPrice,
                                                                 @RequestParam(required = false) Double maxPrice,
                                                                 @RequestParam(required = false, defaultValue = "NoOrder") BookOrderEnum order) {

        List<BookResponse> books = bookService.findAll(idSeller,author,category,minPrice,maxPrice,order);
        if(books != null) {
             return new ResponseEntity<>(books, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


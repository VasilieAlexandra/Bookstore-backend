package com.example.demo.controller;

import com.example.demo.model.dto.response.BookResponse;
import com.example.demo.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/users/{user_id}/books")
@CrossOrigin("*")
public class BookAdminController {

    private final BookService bookService;


    public BookAdminController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllForUser(@PathVariable(name = "user_id") String userId) {
        List<BookResponse> bookResponse = bookService.getAllByIdOwner(userId);

        if (bookResponse != null) {
            return new ResponseEntity<>(bookResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping
    public ResponseEntity<BookResponse> addUserBook(@PathVariable(name = "user_id") String id,
                                                    @Valid @RequestParam("book") String bookRequest,
                                                    @RequestParam("image") MultipartFile multipartFile
    ) {

        try {
            BookResponse bookResponse = bookService.addBook(bookRequest, multipartFile, id);
            if (bookResponse != null)
                return new ResponseEntity<>(bookResponse, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @PutMapping
    public ResponseEntity<BookResponse> updateUserBook(@PathVariable(name = "user_id") String id,
                                                       @Valid @RequestParam("book") String bookRequest,
                                                       @RequestParam(value = "image", required = false) MultipartFile multipartFile
    ) {

        try {
            BookResponse bookResponse = bookService.updateBook(bookRequest, multipartFile, id);
            if (bookResponse != null)
                return new ResponseEntity<>(bookResponse, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @DeleteMapping("/{book_id}")
    public ResponseEntity<Boolean> deleteUserBook(@PathVariable(name = "user_id") String id,
                                                  @PathVariable(name = "book_id") Long bookId) {

        if (bookService.deleteBook(bookId, id))
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

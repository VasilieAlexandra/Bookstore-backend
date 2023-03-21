package com.example.demo.controller;

import com.example.demo.model.dto.request.BookRequest;
import com.example.demo.model.dto.response.BookResponse;
import com.example.demo.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/users/{user_id}/books")
public class BookAdminController {

    private final BookService bookService;
    private Logger log =  Logger.getLogger(this.getClass().getName());

    public BookAdminController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookResponse> addUserBook(@PathVariable(name = "user_id") String id,
                                              @Valid @RequestParam("book") String bookRequest,
                                              @RequestParam("image") MultipartFile multipartFile
    ) throws IOException {
//
        BookRequest book = new ObjectMapper().readValue(bookRequest,BookRequest.class);
//        log.warning(book.toString());
//        return new ResponseEntity<>(multipartFile.getName(), HttpStatus.OK);
        //log.warning(Arrays.toString(multipartFile.getBytes()));
        try {
            BookResponse bookResponse = bookService.addBook(book, multipartFile, id);
            if (bookResponse != null)
                return new ResponseEntity<>(bookResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @PutMapping("/{book_id}")
    public ResponseEntity<BookResponse> updateUserBook(@PathVariable(name = "user_id") String id,
                                                       @PathVariable(name = "book_id") Long bookId,
                                                       @Valid @RequestParam("book") String bookRequest,
                                                       @RequestParam("image") MultipartFile multipartFile
    ) throws IOException {

        BookRequest book = new ObjectMapper().readValue(bookRequest,BookRequest.class);

        try {
            BookResponse bookResponse = bookService.updateBook(bookId, book, multipartFile, id);
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

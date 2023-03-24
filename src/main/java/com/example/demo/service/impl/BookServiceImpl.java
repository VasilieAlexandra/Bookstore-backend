package com.example.demo.service.impl;

import com.example.demo.model.dto.CategoryDto;
import com.example.demo.model.dto.request.BookRequest;
import com.example.demo.model.dto.response.BookResponse;
import com.example.demo.model.entity.Book;
import com.example.demo.model.entity.Category;
import com.example.demo.model.util.BookFilterUtil;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import com.example.demo.service.FirebaseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final FirebaseService firebaseService;


    @Override
    public BookResponse addBook(String bookRequest, MultipartFile multipartFile, String sellerId) throws IOException {

        BookRequest newBook = new ObjectMapper().readValue(bookRequest, BookRequest.class);

        Book dbBook = this.modelMapper.map(newBook, Book.class);
        if (firebaseService.checkUserExists(sellerId))
            dbBook.setIdOwner(sellerId);
        else throw new RuntimeException("User does not exist");

        if (!multipartFile.isEmpty())
            dbBook.setImage(multipartFile.getBytes());
        else throw new RuntimeException("No image uploaded");
        dbBook.setCategories(newBook.getCategories());

        this.bookRepository.save(dbBook);

        return this.modelMapper.map(dbBook, BookResponse.class);
    }

    @Override
    public List<BookResponse> getAvailableBooks(String idSeller, String categoriesDto,
                                                String minPrice, String maxPrice) {

        List<Book> allBooks = bookRepository.getAvailableBooks(idSeller);

        List<Category> categories = new ArrayList<>();

        if (!categoriesDto.equals("")) {
            ObjectMapper mapper = new ObjectMapper();
            List<CategoryDto> categoryList = null;
            try {
                categoryList = mapper.readValue(categoriesDto, new TypeReference<>() {
                });
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            categories = categoryList.stream()
                    .map(c -> this.modelMapper.map(c, Category.class))
                    .toList();
        }
        Predicate<Book> bookPredicate = BookFilterUtil.createBookFilter(categories, minPrice, maxPrice);


        return allBooks.stream()
                .filter(bookPredicate)
                .map(book -> this.modelMapper.map(book, BookResponse.class))
                .toList();
    }

    @Override
    public List<BookResponse> getBooksAuthorOrTitle(String sequence, String idUser) {

        List<Book> filteredBooks = bookRepository.getBooksAuthorOrTitle(sequence, idUser);
        return filteredBooks.stream()
                .map(book -> this.modelMapper.map(book, BookResponse.class))
                .toList();
    }

    @Override
    public List<BookResponse> getAllByIdOwner(String idUser) {

        return bookRepository.getAllByIdOwner(idUser).stream()
                .map(book -> modelMapper.map(book, BookResponse.class))
                .toList();
    }


    @Override
    public BookResponse getBookById(Long bookId) {

        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isPresent()) {
            byte[] image = bookOptional.get().getImage();
            BookResponse bookResponse = this.modelMapper.map(bookOptional.get(), BookResponse.class);
            bookResponse.setImage(image);
            return bookResponse;
        }
        return null;
    }

    @Override
    public BookResponse updateBook(String bookRequest,
                                   MultipartFile multipartFile,
                                   String sellerId) throws IOException {

        BookRequest updatedBook = new ObjectMapper().readValue(bookRequest, BookRequest.class);
        Optional<Book> book;

        if (firebaseService.checkUserExists(sellerId))
            book = bookRepository.findById(updatedBook.getId());
        else throw new RuntimeException("User does not exist");

        if (book.isPresent()) {
            Book foundBook = book.get();

            if (multipartFile != null)
                foundBook.setImage(multipartFile.getBytes());

            if (foundBook.getIdOwner().equals(sellerId)) {
                foundBook.setCategories(updatedBook.getCategories());
                foundBook.setName(updatedBook.getName());
                foundBook.setAuthor(updatedBook.getAuthor());
                foundBook.setPrice(updatedBook.getPrice());
                foundBook.setQuantity(updatedBook.getQuantity());
                return this.modelMapper.map(bookRepository.save(foundBook), BookResponse.class);
            } else throw new RuntimeException("You don't have the right to update this book");
        }
        return null;
    }

    @Override
    public boolean deleteBook(Long bookId, String sellerId) {
        Optional<Book> book;
        if (firebaseService.checkUserExists(sellerId))
            book = bookRepository.findById(bookId);
        else throw new RuntimeException("User does not exist");

        if (book.isPresent() && book.get().getIdOwner().equals(sellerId)) {
            bookRepository.delete(book.get());
            return true;
        }
        return false;
    }
}

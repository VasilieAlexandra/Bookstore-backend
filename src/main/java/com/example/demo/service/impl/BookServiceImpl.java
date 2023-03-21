package com.example.demo.service.impl;

import com.example.demo.model.dto.request.BookRequest;
import com.example.demo.model.dto.response.BookResponse;
import com.example.demo.model.entity.Book;
import com.example.demo.model.entity.BookOrderEnum;
import com.example.demo.model.entity.Category;
import com.example.demo.model.util.BookFilterUtil;
import com.example.demo.model.util.BookSortUtil;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import com.example.demo.service.FirebaseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final FirebaseService firebaseService;
    private Logger log =  Logger.getLogger(this.getClass().getName());


    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper, FirebaseService firebaseService) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
        this.firebaseService = firebaseService;
    }

    @Override
    public BookResponse addBook(BookRequest newBook, MultipartFile multipartFile, String sellerId) throws IOException {

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
    public List<BookResponse> findAll(String idSeller, List<String> author, List<Category> category,
                                      Double minPrice, Double maxPrice, BookOrderEnum order) {

        List<Book> allBooks = bookRepository.findAll();
        log.warning(allBooks.toString());
//        log.warning("seller: "+idSeller );
//        log.warning("author: "+author.isEmpty() );
//        log.warning("category: "+category.isEmpty() );
//        log.warning("minPrice: "+minPrice );
//        log.warning("maxPrice: "+maxPrice );

        Predicate<Book> bookPredicate = BookFilterUtil.createBookFilter(idSeller, author, category, minPrice, maxPrice);
        //Comparator<Book> comp = BookSortUtil.createBookSorted(order);


        return allBooks.stream()
                .filter(bookPredicate)
                //.sorted(comp)
                .map(book -> this.modelMapper.map(book, BookResponse.class))
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
    public BookResponse updateBook(Long bookId,
                                   BookRequest updatedBook,
                                   MultipartFile multipartFile,
                                   String sellerId) throws IOException {
        Optional<Book> book;

        if (firebaseService.checkUserExists(sellerId))
            book = bookRepository.findById(bookId);
        else throw new RuntimeException("User does not exist");

        if (book.isPresent()) {
            Book foundBook = book.get();
            if (foundBook.getIdOwner().equals(sellerId)) {
                foundBook.setCategories(updatedBook.getCategories());
                foundBook.setImage(multipartFile.getBytes());
                foundBook.setName(updatedBook.getName());
                foundBook.setAuthor(updatedBook.getAuthor());
                foundBook.setPrice(updatedBook.getPrice());
                foundBook.setQuantity(updatedBook.getQuantity());
                foundBook.setImage(multipartFile.getBytes());
                return this.modelMapper.map(bookRepository.save(foundBook), BookResponse.class);
            }
            else throw new RuntimeException("You don't have the right to update this book");
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

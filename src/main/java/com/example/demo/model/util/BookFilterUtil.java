package com.example.demo.model.util;

import com.example.demo.model.entity.Book;
import com.example.demo.model.entity.Category;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class BookFilterUtil{

   public static Predicate<Book> createBookFilter(String idSeller,
                                                  List<String> author,
                                                  List<Category> category,
                                                  Double minPrice,
                                                  Double maxPrice){

       Predicate<Book> bookPredicate = Objects::nonNull;
       if (!author.isEmpty()) {
           Predicate<Book> authorPredicate = book -> author.contains(book.getAuthor());
           bookPredicate = bookPredicate.and(authorPredicate);
       }
       if (!category.isEmpty()) {
           Predicate<Book> categoryPredicate = book -> category.stream().anyMatch(book.getBookCategories()::contains);
           bookPredicate = bookPredicate.and(categoryPredicate);
       }
       if (minPrice != null) {
           Predicate<Book> minPricePredicate = book -> book.getPrice() >= minPrice;
           bookPredicate = bookPredicate.and(minPricePredicate);
       }
       if (maxPrice != null) {
           Predicate<Book> maxPricePredicate = book -> book.getPrice() <= maxPrice;
           bookPredicate = bookPredicate.and(maxPricePredicate);
       }
       if (idSeller != null) {
           Predicate<Book> sellerPredicate = book -> book.getIdOwner().equals(idSeller);
           bookPredicate = bookPredicate.and(sellerPredicate);
       }
       return bookPredicate;
   }

}

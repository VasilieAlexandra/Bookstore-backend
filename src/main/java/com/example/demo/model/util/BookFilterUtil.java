package com.example.demo.model.util;

import com.example.demo.model.entity.Book;
import com.example.demo.model.entity.Category;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class BookFilterUtil {

    public static Predicate<Book> createBookFilter(List<Category> categories,
                                                   String minPrice,
                                                   String maxPrice) {

        Predicate<Book> bookPredicate = Objects::nonNull;

        if (categories != null && !categories.isEmpty()) {
            Predicate<Book> categoryPredicate = book -> !Collections.disjoint(book.getCategories(), categories);
            bookPredicate = bookPredicate.and(categoryPredicate);
        }
        if (minPrice != null && !minPrice.isEmpty()) {
            Predicate<Book> minPricePredicate = book -> book.getPrice() >= Long.parseLong(minPrice);
            bookPredicate = bookPredicate.and(minPricePredicate);
        }
        if (maxPrice != null && !maxPrice.isEmpty()) {
            Predicate<Book> maxPricePredicate = book -> book.getPrice() <= Long.parseLong(maxPrice);
            bookPredicate = bookPredicate.and(maxPricePredicate);
        }

        return bookPredicate;
    }

}

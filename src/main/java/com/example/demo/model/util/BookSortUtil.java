package com.example.demo.model.util;

import com.example.demo.model.entity.Book;
import com.example.demo.model.entity.BookOrderEnum;

import java.util.Comparator;

public class BookSortUtil {

    public static Comparator<Book> createBookSorted(BookOrderEnum order){

        Comparator<Book> bookComparator = (b1,b2) -> 0;
        switch (order) {
            case MIN_MAX_PRICE -> bookComparator = Comparator.comparing(Book::getPrice);
            case MAX_MIN_PRICE -> bookComparator = (b1, b2) -> b2.getPrice().compareTo(b1.getPrice());
            case ALPHABETICALLY_DESC -> bookComparator = (b1, b2) -> b2.getName().compareTo(b1.getName());
            case ALPHABETICALLY_ASC -> bookComparator = Comparator.comparing(Book::getName);
            default -> {
                return bookComparator;
            }
        }

        return bookComparator;
    }
}

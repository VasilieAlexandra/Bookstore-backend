package com.example.demo.repository;

import com.example.demo.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("""
            SELECT b FROM Book AS b WHERE (LOWER(b.name) LIKE LOWER(CONCAT('%', :str, '%')) OR\s
            LOWER(b.author) LIKE LOWER(CONCAT('%', :str, '%')))\s
            AND b.idOwner!=(:id)\s
            AND b.quantity > 0""")
    List<Book> getBooksAuthorOrTitle(@Param("str") String sequence, @Param("id") String id);

    @Modifying
    @Query("UPDATE Book b SET b.quantity = b.quantity - (:q) WHERE b.id = (:id)")
    void updateQuantity(@Param("id") Long bookId, @Param("q") Long quantity);

    List<Book> getAllByIdOwner(String id);

    @Query("SELECT b FROM Book AS b WHERE b.idOwner!=(:id) AND b.quantity > 0")
    List<Book> getAvailableBooks(@Param("id") String id);


}

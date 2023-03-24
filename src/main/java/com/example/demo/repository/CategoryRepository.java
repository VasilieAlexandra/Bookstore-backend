package com.example.demo.repository;

import com.example.demo.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c FROM Category as c INNER JOIN Book as b ON c.id not in (:categories) WHERE b.id=:id")
    List<Category> getCategoriesNotInBook(@Param("id") Long bookId, @Param("categories") List<Long> categories);
}

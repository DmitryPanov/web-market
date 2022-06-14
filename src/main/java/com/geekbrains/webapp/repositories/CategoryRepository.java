package com.geekbrains.webapp.repositories;

import com.geekbrains.webapp.model.Category;
import com.geekbrains.webapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByTitle(String title);

    @Query("select c from Category c join fetch c.products where c.id = :id")
    Optional<Category> findByIdWithProducts(Long id);
}

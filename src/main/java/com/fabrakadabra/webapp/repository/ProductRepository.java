package com.fabrakadabra.webapp.repository;

import com.fabrakadabra.webapp.model.Category;
import com.fabrakadabra.webapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    void deleteByID(Long id);
    List<Product> findByCategory(Category category);
}

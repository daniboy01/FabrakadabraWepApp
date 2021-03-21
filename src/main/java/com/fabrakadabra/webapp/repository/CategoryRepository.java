package com.fabrakadabra.webapp.repository;

import com.fabrakadabra.webapp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByID(Long id);
    boolean existsByName(String name);
}

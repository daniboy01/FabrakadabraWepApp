package com.fabrakadabra.webapp.repository;

import com.fabrakadabra.webapp.model.ProductImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImgRepository extends JpaRepository<ProductImg, Long> {
}

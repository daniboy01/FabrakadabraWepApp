package com.fabrakadabra.webapp.service;

import com.fabrakadabra.webapp.repository.ProductCategoryRepository;
import com.fabrakadabra.webapp.repository.ProductImgRepository;
import com.fabrakadabra.webapp.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;
    private ProductImgRepository productImgRepository;
    private ProductCategoryRepository productCategoryRepository;
}

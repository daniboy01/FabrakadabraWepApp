package com.fabrakadabra.webapp.controller;

import com.fabrakadabra.webapp.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @GetMapping("/getAll")
    public String getAllProduct(){
        return "";
    }
}

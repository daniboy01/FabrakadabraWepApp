package com.fabrakadabra.webapp.controller;

import com.fabrakadabra.webapp.dto.product.CreateProductDto;
import com.fabrakadabra.webapp.dto.product.ProductDto;
import com.fabrakadabra.webapp.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductDto>> getAllProduct(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.getById(id));
    }

    @PostMapping("/createNewProduct")
    public ResponseEntity<ProductDto> createNewProduct(@RequestBody CreateProductDto dto){
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.createNewProduct(dto));
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto dto){
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.updateProduct(dto));
    }

    @PostMapping("/deleteProduct")
    public ResponseEntity<String> deleteProduct(@RequestBody ProductDto dto){
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.deleteProduct(dto));
    }
}

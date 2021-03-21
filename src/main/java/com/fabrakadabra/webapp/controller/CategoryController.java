package com.fabrakadabra.webapp.controller;

import com.fabrakadabra.webapp.dto.category.CategoryDto;
import com.fabrakadabra.webapp.dto.category.CreateCategoryDto;
import com.fabrakadabra.webapp.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
public class CategoryController {
    private CategoryService categoryService;


    @GetMapping("/all")
    public ResponseEntity<List<CategoryDto>> getAll(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.getAll());
    }

    @PostMapping("/createNew")
    public ResponseEntity<CategoryDto> createNew(@RequestBody CreateCategoryDto dto){
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.save(dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.delete(id));
    }
}

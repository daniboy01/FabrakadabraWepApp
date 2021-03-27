package com.fabrakadabra.webapp.service;

import com.fabrakadabra.webapp.dto.category.CategoryDto;
import com.fabrakadabra.webapp.dto.category.CreateCategoryDto;
import com.fabrakadabra.webapp.model.Category;
import com.fabrakadabra.webapp.model.Product;
import com.fabrakadabra.webapp.repository.CategoryRepository;
import com.fabrakadabra.webapp.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    public CategoryDto save(CreateCategoryDto dto){
        if (categoryRepository.existsByName(dto.getName())){
            throw new IllegalArgumentException("Category already exists!");
        }
        Category newCategory = new Category();
        newCategory.setName(dto.getName());
        newCategory = categoryRepository.save(newCategory);

        return new CategoryDto(newCategory.getID(),newCategory.getName());
    }

    public List<CategoryDto> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private CategoryDto mapToDto(Category category){
        return CategoryDto.builder()
                .ID(category.getID())
                .name(category.getName())
                .build();
    }

    public String delete(Long id) {
        if (!categoryRepository.existsById(id)){
            return "No category found by id : " + id;
        }

        Category category = categoryRepository.findByID(id);
        String categoryName = category.getName();

        List<Product> productsWithCategory = productRepository.findByCategory(category);

        for (Product p : productsWithCategory){
            p.setCategory(null);
        }

        categoryRepository.deleteById(id);
        return categoryName + " succesfully deleted";
    }
}

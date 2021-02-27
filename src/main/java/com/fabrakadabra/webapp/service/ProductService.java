package com.fabrakadabra.webapp.service;

import com.fabrakadabra.webapp.dto.ProductDto;
import com.fabrakadabra.webapp.dto.ProductImgDto;
import com.fabrakadabra.webapp.model.Product;
import com.fabrakadabra.webapp.model.ProductImg;
import com.fabrakadabra.webapp.repository.ProductCategoryRepository;
import com.fabrakadabra.webapp.repository.ProductImgRepository;
import com.fabrakadabra.webapp.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;
    private ProductImgRepository productImgRepository;
    private ProductCategoryRepository productCategoryRepository;

    public List<ProductDto> getAll() {
        return productRepository.findAll()
                .stream()
                .map(this::maptoDto)
                .collect(Collectors.toList());
    }

    public ProductDto createNewProduct(ProductDto dto) {
        Product saveProduct = productRepository.save(mapDtoToModel(dto));
        dto.setID(saveProduct.getID());
        saveProduct.setImages(mapImgDtosToModel(dto, saveProduct));
        dto.setImages(mapImgsModelToDto(saveProduct));
        productRepository.save(saveProduct);
        return dto;
    }

    public ProductDto updateProduct(ProductDto dto) {
        Product update = productRepository.findById(dto.getID()).get();
        update = mapDtoToModel(dto);
        productRepository.save(update);
        return dto;
    }

    public String deleteProduct(ProductDto dto) {
        Product product = productRepository.findById(dto.getID()).get();
        productRepository.delete(product);
        return dto.getID() + " számú termék kitörölve";
    }


    private Product mapDtoToModel(ProductDto dto){
        return Product.builder()
                .ID(dto.getID())
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .categoryID(dto.getCategoryID())
                .build();
    }


    private ProductDto maptoDto(Product product){
        return ProductDto.builder()
                .ID(product.getID())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .categoryID(product.getCategoryID())
                .images(mapImgsModelToDto(product))
                .build();
    }

    private List<ProductImg> mapImgDtosToModel(ProductDto dto,Product product){
        List<ProductImg> imgs = new ArrayList<>();
        for(ProductImgDto p : dto.getImages()){
            ProductImg save = new ProductImg(p.getURL(),product);
            imgs.add(productImgRepository.save(save));
        }
        return imgs;
    }


    private List<ProductImgDto> mapImgsModelToDto(Product product){
        List<ProductImgDto> imgs = new ArrayList<>();
        for(ProductImg img : product.getImages()){
            imgs.add(new ProductImgDto(img.getID(),img.getURL()));
        }
        return imgs;
    }
}

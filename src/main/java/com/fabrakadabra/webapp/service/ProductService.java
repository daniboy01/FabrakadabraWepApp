package com.fabrakadabra.webapp.service;

import com.fabrakadabra.webapp.dto.CreateProductDto;
import com.fabrakadabra.webapp.dto.DimensionDTO;
import com.fabrakadabra.webapp.dto.ProductDto;
import com.fabrakadabra.webapp.dto.ProductImgDto;
import com.fabrakadabra.webapp.model.Dimensions;
import com.fabrakadabra.webapp.model.Product;
import com.fabrakadabra.webapp.model.ProductCategory;
import com.fabrakadabra.webapp.model.ProductImg;
import com.fabrakadabra.webapp.repository.DimensionsRepository;
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
    private DimensionsRepository dimensionsRepository;


    public List<ProductDto> getAll() {
        return productRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public ProductDto createNewProduct(CreateProductDto dto) {
        if (dto == null){
            throw new IllegalArgumentException("Dto is null or empty");
        }

        Product save = new Product();
        save.setName(dto.getName());
        save.setPrice(dto.getPrice());
        save.setDescription(dto.getDescription());
        save.setCategory(ProductCategory.valueOf(dto.getCategory()));
        save.setImages(saveNewImages(dto.getImages(),save));
        save.setDimensions(saveNewDimensions(dto.getDimensions(),save));

        return mapToDto(productRepository.save(save));
    }

    public ProductDto updateProduct(ProductDto dto) {
        if (!productRepository.existsById(dto.getID())){
            throw new IllegalArgumentException("There is no product by " + dto.getID());
        }

        Product product = productRepository.findById(dto.getID()).get();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setDimensions(saveNewDimensions(dto.getDimensions(),product));
        product.setImages(saveNewImages(dto.getImages(),product));
        product.setCategory(ProductCategory.valueOf(dto.getCategory()));

        return mapToDto(productRepository.save(product));
    }

    public String deleteProduct(ProductDto dto) {
        if (!productRepository.existsById(dto.getID())){
            throw new IllegalArgumentException("There is no product by " + dto.getID());
        }

        productRepository.deleteByID(dto.getID());

        return "Product " + dto.getID() + " successfully deleted!";
    }

    private ProductDto mapToDto(Product product) {
        return ProductDto.builder()
                .ID(product.getID())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .category(product.getCategory().toString())
                .images(mapImgToDto(product.getImages()))
                .dimensions(mapDimensionToDto(product.getDimensions()))
                .build();
    }

    private Dimensions saveNewDimensions(DimensionDTO dto, Product product){
        Dimensions save = new Dimensions(
                dto.getWidthInMetre(),
                dto.getHeightInMetre(),
                dto.getDepthInMetre(),
                dto.getWeightInKg(),
                dto.getMaterial(),
                product
        );
        return dimensionsRepository.save(save);
    }

    private List<ProductImg> saveNewImages(List<ProductImgDto> dtos, Product product){
        List<ProductImg> imgs = new ArrayList<>();
        for(ProductImgDto dto : dtos){
            ProductImg save = new ProductImg(
                    dto.getURL(),
                    product
            );
            imgs.add(productImgRepository.save(save));
        }
        return imgs;
    }

    private List<ProductImgDto> mapImgToDto(List<ProductImg> imgs){
        List<ProductImgDto> productImgDtos = new ArrayList<>();
        for(ProductImg img : imgs){
            productImgDtos.add(
                    new ProductImgDto(img.getID(),img.getURL())
            );
        }
        return productImgDtos;
    }

    private DimensionDTO mapDimensionToDto(Dimensions dimensions){
        return DimensionDTO.builder()
                .weightInKg(dimensions.getWeightInKg())
                .depthInMetre(dimensions.getDepthInMetre())
                .material(dimensions.getMaterial())
                .widthInMetre(dimensions.getWidthInMetre())
                .heightInMetre(dimensions.getHeightInMetre())
                .build();
    }
}

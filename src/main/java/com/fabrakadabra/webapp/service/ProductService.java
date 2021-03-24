package com.fabrakadabra.webapp.service;

import com.fabrakadabra.webapp.dto.dimension.DimensionDTO;
import com.fabrakadabra.webapp.dto.product.CreateProductDto;
import com.fabrakadabra.webapp.dto.product.NewImageDto;
import com.fabrakadabra.webapp.dto.product.ProductDto;
import com.fabrakadabra.webapp.dto.product.ProductImgDto;
import com.fabrakadabra.webapp.model.Dimensions;
import com.fabrakadabra.webapp.model.Product;
import com.fabrakadabra.webapp.model.ProductImg;
import com.fabrakadabra.webapp.repository.CategoryRepository;
import com.fabrakadabra.webapp.repository.DimensionsRepository;
import com.fabrakadabra.webapp.repository.ProductImgRepository;
import com.fabrakadabra.webapp.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;
    private ProductImgRepository productImgRepository;
    private DimensionsRepository dimensionsRepository;
    private CategoryRepository categoryRepository;
    private ImageService imageService;


    public List<ProductDto> getAll() {
        return productRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public ProductDto getById(long id) {
        if(!productRepository.existsById(id)){
            throw new IllegalArgumentException();
        }

        return mapToDto(productRepository.findById(id).get());
    }

    public ProductDto createNewProduct(CreateProductDto dto) {
        if (dto == null){
            throw new IllegalArgumentException("Dto is null or empty");
        }

        Product save = productRepository.save(new Product());
        save.setName(dto.getName());
        save.setPrice(dto.getPrice());
        save.setDescription(dto.getDescription());
        saveNewDimensions(dto.getDimensions(),save);
        saveNewImages(dto.getImages(), save);
        if (dto.getCategoryID() == null){
            save.setCategory(null);
        }
        else {
            save.setCategory(categoryRepository.findByID(dto.getCategoryID()));
        }

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
        //TODO: KÉPEK SZERKESZTÉSÉT MEGOLDANI A JÖVŐBEN JELENLEG NINCS MEGOLDVA
        //product.setImages(saveNewImages(dto.getImages(),product));

        if(dto.getCategoryID() == null){
            product.setCategory(null);
        }
        else {
            product.setCategory(categoryRepository.findByID(dto.getCategoryID()));
        }


        return mapToDto(productRepository.save(product));
    }

    @Transactional
    public String deleteProduct(Long id){
        if (!productRepository.existsById(id)){
            return  "There is no product by " + id;
        }
        imageService.deleteImageFromFileSys(id+".jpg");
        String productName = productRepository.findById(id).get().getName();
        productRepository.deleteByID(id);

        return  productName + " successfully deleted!";
    }

    public ProductDto mapToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setID(product.getID());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setDimensions(mapDimensionToDto(product.getDimensions()));
        dto.setImages(mapImgToDto(product.getImages()));
        if (product.getCategory() == null){
            dto.setCategoryID(null);
        }
        else {
            dto.setCategoryID(product.getCategory().getID());
        }
        return dto;
    }

    private Dimensions saveNewDimensions(DimensionDTO dto, Product product){
        Dimensions save = new Dimensions(
                dto.getWidthInMetre(),
                dto.getHeightInMetre(),
                dto.getDepthInMetre(),
                dto.getWeightInKg(),
                dto.getMaterial(),
                product.getID()
        );
        product.setDimensions(save);
        return dimensionsRepository.save(save);
    }

    private List<ProductImg> saveNewImages(List<NewImageDto> dtos, Product product){
        List<ProductImg> imgs = new ArrayList<>();
        for(NewImageDto dto : dtos){
            ProductImg save = new ProductImg(
                    dto.getURL(),
                    product.getID()
            );
            product.setImages(imgs);
            imgs.add(productImgRepository.save(save));
        }

        return imgs;
    }

    private List<ProductImgDto> mapImgToDto(List<ProductImg> imgs){
        if (imgs == null){
            return new ArrayList<ProductImgDto>();
        }
        List<ProductImgDto> productImgDtos = new ArrayList<>();
        for(ProductImg img : imgs){
            productImgDtos.add(
                    new ProductImgDto(img.getID(),img.getURL())
            );
        }
        return productImgDtos;
    }

    private DimensionDTO mapDimensionToDto(Dimensions dimensions){
        if (dimensions == null){
            return new DimensionDTO();
        }
        DimensionDTO dimensionDTO = DimensionDTO.builder()
                .weightInKg(dimensions.getWeightInKg())
                .depthInMetre(dimensions.getDepthInMetre())
                .material(dimensions.getMaterial())
                .widthInMetre(dimensions.getWidthInMetre())
                .heightInMetre(dimensions.getHeightInMetre())
                .build();
        return dimensionDTO;
    }

}

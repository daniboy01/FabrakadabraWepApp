package com.fabrakadabra.webapp.dto;

import com.fabrakadabra.webapp.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long ID;
    private String name;
    private String description;
    private int price;
    private List<ProductImgDto> images;
    private Long categoryID;
}

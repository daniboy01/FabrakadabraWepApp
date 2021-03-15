package com.fabrakadabra.webapp.dto;

import com.fabrakadabra.webapp.model.Dimensions;
import com.fabrakadabra.webapp.model.Product;
import com.fabrakadabra.webapp.model.ProductCategory;
import com.fabrakadabra.webapp.model.ProductImg;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
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
    private String category;
    private List<ProductImgDto> images;
    private DimensionDTO dimensions;
}

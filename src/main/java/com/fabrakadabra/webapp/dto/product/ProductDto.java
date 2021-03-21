package com.fabrakadabra.webapp.dto.product;

import com.fabrakadabra.webapp.dto.category.CategoryDto;
import com.fabrakadabra.webapp.dto.dimension.DimensionDTO;
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
    private String category;
    private List<ProductImgDto> images;
    private DimensionDTO dimensions;
    private Long categoryID;
}

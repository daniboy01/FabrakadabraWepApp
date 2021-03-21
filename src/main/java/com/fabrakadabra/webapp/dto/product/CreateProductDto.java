package com.fabrakadabra.webapp.dto.product;

import com.fabrakadabra.webapp.dto.dimension.DimensionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductDto {
    private String name;
    private String description;
    private int price;
    private String category;
    private List<NewImageDto> images;
    private DimensionDTO dimensions;

}

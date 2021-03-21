package com.fabrakadabra.webapp.dto.dimension;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DimensionDTO {
    private double widthInMetre;
    private double heightInMetre;
    private double depthInMetre;
    private double weightInKg;
    private String material;
}

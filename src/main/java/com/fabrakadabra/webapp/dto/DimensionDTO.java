package com.fabrakadabra.webapp.dto;

import com.fabrakadabra.webapp.model.PlayGround;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DimensionDTO {
    private Long ID;
    private double widthInMetre;
    private double heightInMetre;
    private double depthInMetre;
    private double weightInKg;
    private String material;
    private PlayGround playGround;
}

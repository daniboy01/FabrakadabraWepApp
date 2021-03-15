package com.fabrakadabra.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Dimensions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private double widthInMetre;
    private double heightInMetre;
    private double depthInMetre;
    private double weightInKg;
    private String material;

    @OneToOne
    private Product product;

    public Dimensions(double widthInMetre,double heightInMetre,double depthInMetre,double weightInKg,String material, Product product) {
        this.widthInMetre = widthInMetre;
        this.heightInMetre = heightInMetre;
        this.depthInMetre = depthInMetre;
        this.weightInKg = weightInKg;
        this.material = material;
        this.product = product;
    }
}

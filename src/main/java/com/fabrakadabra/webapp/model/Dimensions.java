package com.fabrakadabra.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
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
    private PlayGround playGround;

}

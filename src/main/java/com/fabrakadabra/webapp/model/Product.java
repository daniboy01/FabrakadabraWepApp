package com.fabrakadabra.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.awt.*;
import java.time.Instant;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "termék")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String name;
    private String description;
    private int price;
    private Instant createdAt;
    private ProductCategory category;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<ProductImg> images;

    @OneToOne
    private Dimensions dimensions;

    public Product(String name, String desc, int price, String category, List<ProductImg> imgs, Dimensions dimension) {
        this.name = name;
        this.description = desc;
        this.price = price;
        this.category = ProductCategory.valueOf(category);
        this.images = imgs;
        this.dimensions = dimension;
        this.createdAt = Instant.now();
    }
}

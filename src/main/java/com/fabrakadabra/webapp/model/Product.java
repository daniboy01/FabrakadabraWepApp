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

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<ProductImg> images;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Dimensions dimensions;

    @ManyToOne
    private Category category;
}

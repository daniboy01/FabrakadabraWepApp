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
public class ProductImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String URL;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Product product;

    public ProductImg(String URL, Product product) {
        this.URL = URL;
        this.product = product;
    }
}

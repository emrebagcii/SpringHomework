package com.bilgeadam.homework.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private int productId;

    @Column(name="product_name")
    private String productName;

    @Column(name = "unit_price")
    private double unitPrice;

    @Column(name="units_in_stock")
    private int stock;

    private int discontinued;
}

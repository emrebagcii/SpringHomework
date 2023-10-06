package com.bilgeadam.homework.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="order_id")
    private int orderId;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name="employee_id")
    private int employeeId;

    @Column(name="order_date")
    private LocalDateTime orderDate;

    @Column(name="ship_via")
    private int shipId;

    @Column(name = "product_id")
    private int productId;

    private int quantity;

    private double discount;

    private double price;
}

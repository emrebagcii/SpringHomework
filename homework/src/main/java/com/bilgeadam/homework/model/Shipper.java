package com.bilgeadam.homework.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="shippers")
public class Shipper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="shipper_id")
    private int shipperId;

    @Column(name="company_name")
    private String shipperName;

    @Column(name="phone")
    private String phone;
}

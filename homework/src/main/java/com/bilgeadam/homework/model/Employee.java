package com.bilgeadam.homework.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="employee_id")
    private int employeeId;

    @Column(name="last_name")
    private String lastName;

    @Column(name="first_name")
    private String firstName;
}

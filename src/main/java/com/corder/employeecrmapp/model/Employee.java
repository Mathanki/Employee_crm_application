package com.corder.employeecrmapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="employee")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;
}

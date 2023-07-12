package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Person   {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}

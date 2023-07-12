package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskId;

    private String title;

    private String description;

    private LocalDateTime period;

    private int duration;

    private boolean finished;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToOne
    @JoinColumn(name = "department_id")
    private Department department;

}

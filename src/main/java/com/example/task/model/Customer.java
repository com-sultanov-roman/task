package com.example.task.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(columnDefinition = "VARCHAR(14) NOT NULL")
    private String name;

    @Column(columnDefinition = "CHAR(3)")
    private String country;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(length = 50)
    private String phone;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();
}

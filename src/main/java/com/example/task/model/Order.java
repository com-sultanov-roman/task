package com.example.task.model;


import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private java.util.Date date;

    @ManyToOne
    @JoinColumn(name="cust_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Detail> detail = new ArrayList<>();

    @OneToOne(mappedBy = "order")
    private Invoice invoice;
}

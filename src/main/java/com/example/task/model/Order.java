package com.example.task.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private java.util.Date date;

    @ManyToOne
    @JoinColumn(name="cust_id")
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Detail> detail = new ArrayList<>();

    @JsonIgnore
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Invoice invoice;
}

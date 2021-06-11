package com.example.task.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(columnDefinition = "VARCHAR(10)")
    private String name;

    @Column(columnDefinition = "VARCHAR(20)")
    private String description;

    @Column(columnDefinition = "NUMERIC(6, 2)")
    private BigDecimal price;

    @Column(columnDefinition = "VARCHAR(1024)")
    private String photo;

    @ManyToOne
    @JoinColumn(name="category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Detail> details = new ArrayList<>();

}

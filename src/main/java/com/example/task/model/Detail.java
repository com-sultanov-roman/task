package com.example.task.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Detail {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(columnDefinition = "SMALLINT")
    private short quantity;

    @ManyToOne
    @JoinColumn(name="ord_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name="pr_id", nullable = false)
    private Product product;

}

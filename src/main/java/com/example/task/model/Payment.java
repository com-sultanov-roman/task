package com.example.task.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private java.util.Date timestamp;

    @Column(columnDefinition = "NUMERIC(8,2)", nullable = false)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name="inv_id", nullable = false)
    private Invoice invoice ;
}

package com.example.task.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(columnDefinition = "NUMERIC(8,2)", nullable = false)
    private BigDecimal amount;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private java.util.Date issued;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private java.util.Date due;

    @OneToOne
    @JoinColumn(name = "ord_id")
    private Order order;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Payment> payments = new ArrayList<>();
}

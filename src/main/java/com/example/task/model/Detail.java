package com.example.task.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Detail {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(columnDefinition = "SMALLINT")
    private short quantity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="ord_id")
    private Order order;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="pr_id", nullable = false)
    private Product product;
}

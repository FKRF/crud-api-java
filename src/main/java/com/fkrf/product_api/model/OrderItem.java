package com.fkrf.product_api.model;

import jakarta.persistence.*;

import java.util.UUID;
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private long quantity;

    @Column(nullable = false)
    private double unitPrice;

    @Column(nullable = false)
    private double discount;
}

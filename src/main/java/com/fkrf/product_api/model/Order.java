package com.fkrf.product_api.model;

import com.fkrf.product_api.enums.OrderStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "Binary(16)")
    private UUID id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id")
    private Client client;
    @Column(nullable = false)
    private LocalDateTime orderedAt;
    @Column
    private LocalDateTime paidAt;
    @Column
    private LocalDateTime deliveredAt;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus Status;
    @Column(nullable = false)
    private double totalPrice;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;
}

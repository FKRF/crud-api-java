package com.fkrf.product_api.model;

import com.fkrf.product_api.enums.PhoneType;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "phones")
public class Phone {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @Column(nullable = false)
    private String number;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PhoneType phoneType;
    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id")
    private Client client;
}
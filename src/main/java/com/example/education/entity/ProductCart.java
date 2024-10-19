package com.example.education.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "product_cart")
@Entity
public class ProductCart {
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productid",nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cartid",nullable = false)
    private Cart cart;
}

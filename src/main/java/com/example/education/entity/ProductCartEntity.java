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
public class ProductCartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @Column(name = "quantity", nullable = false)
    private Long quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productid", nullable = false)
    private ProductEntity product;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cartid", nullable = false)
    private CartEntity cart;
}

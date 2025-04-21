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
@Table(name = "products_in_order")
@Entity
public class ProductsInOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @Column(name = "quantity", nullable = false)
    private Long quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderid", nullable = false)
    private OrderEntity order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productid", nullable = false)
    private ProductEntity product;
}

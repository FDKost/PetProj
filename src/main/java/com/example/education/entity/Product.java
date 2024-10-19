package com.example.education.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "product")
@Entity
public class Product {
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "price",nullable = false)
    private Integer price;
    @Column(name = "details",nullable = false)
    private String details;
    @Column(name = "imageurl",nullable = false)
    private String imageURL;
}

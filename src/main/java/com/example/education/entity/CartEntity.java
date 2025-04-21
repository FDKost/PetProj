package com.example.education.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "cart")
@Entity
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @Column(name = "createdin", nullable = false)
    private LocalDate createdIn;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", nullable = false)
    private UserEntity user;
}

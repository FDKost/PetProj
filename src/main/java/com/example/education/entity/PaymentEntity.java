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
@Table(name = "payment")
@Entity
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @Column(name = "total", nullable = false)
    private Long total;
    @Column(name = "checkurl", nullable = false)
    private String checkURL;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private UserEntity user;
}
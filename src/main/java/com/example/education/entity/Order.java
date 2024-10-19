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
@Table(name = "orders")
@Entity
public class Order {
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "status")
    private UUID status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paymentid")
    private Payment payment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addressid")
    private Address address;
}

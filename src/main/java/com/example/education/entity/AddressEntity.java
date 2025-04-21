package com.example.education.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "address")
@Builder
@Entity
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @Column(name = "street")
    private String street;
    @Column(name = "house")
    private String house;
    @Column(name = "apartment")
    private String apartment;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private UserEntity user;

}

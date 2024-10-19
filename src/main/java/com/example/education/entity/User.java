package com.example.education.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "client")
@Entity
public class User {
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(name = "login",nullable = false)
    private String login;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "role",nullable = false)
    private Role role;
}

package com.example.education.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class Address {
    private UUID id;
    private UUID userId;
    private String street;
    private String house;
    private String apartment;


}

package com.example.Education;

import lombok.Data;

@Data
public class Address {
    private Long id_address;
    private Long id_user;
    private String street;
    private String house;
    private String apartment;
}
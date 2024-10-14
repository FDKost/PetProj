package com.example.education.entity;

import lombok.Data;

@Data
public class Address {
    private Long addressId;
    private Integer userId;
    private String street;
    private String house;
    private String apartment;


}

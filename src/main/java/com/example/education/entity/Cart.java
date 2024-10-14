package com.example.education.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Cart {

    private Long cartId;
    private Long userId;
    private Date createdIn;
}

package com.example.Education;

import lombok.Data;

import java.util.Date;

@Data
public class Cart {

    private Long id_cart;
    private Long id_user;
    private Date created_in;
}

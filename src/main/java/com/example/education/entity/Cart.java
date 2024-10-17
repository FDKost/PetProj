package com.example.education.entity;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class Cart {

    private UUID id;
    private UUID userId;
    private Date createdIn;
}

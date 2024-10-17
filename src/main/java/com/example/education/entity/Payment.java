package com.example.education.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class Payment {
    private UUID id;
    private Long total;
    private String checkURL;
    private UUID userId;
}
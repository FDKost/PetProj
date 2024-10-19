package com.example.education.dto.cart;

import com.example.education.entity.User;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
public class CartReadDTO {
    UUID id;
    User userid;
    LocalDate createdin;
}

package com.example.education.dto.cart;

import com.example.education.entity.UserEntity;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
public class CartReadDTO {
    UUID id;
    UserEntity userid;
    LocalDate createdin;
}

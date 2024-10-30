package com.example.education.dto.cart;

import com.example.education.entity.UserEntity;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;

@Value
@FieldNameConstants
public class CartCreateEditDTO {
    UserEntity userid;
    LocalDate createdin;
}

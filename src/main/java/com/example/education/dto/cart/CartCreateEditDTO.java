package com.example.education.dto.cart;

import com.example.education.entity.User;
import lombok.Data;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Value
@FieldNameConstants
public class CartCreateEditDTO {
    User userid;
    LocalDate createdin;
}

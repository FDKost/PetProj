package com.example.education.dto.address;

import com.example.education.entity.User;
import lombok.Value;

import java.util.UUID;

@Value
public class AddressReadDTO {
    UUID id;
    User userId;
    String street;
    String house;
    String apartment;
}

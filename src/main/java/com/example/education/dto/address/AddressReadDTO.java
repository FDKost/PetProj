package com.example.education.dto.address;

import com.example.education.entity.UserEntity;
import lombok.Value;

import java.util.UUID;

@Value
public class AddressReadDTO {
    UUID id;
    UserEntity userId;
    String street;
    String house;
    String apartment;
}

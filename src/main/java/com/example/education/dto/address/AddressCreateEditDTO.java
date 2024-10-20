package com.example.education.dto.address;

import com.example.education.entity.User;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

import java.util.UUID;

@Value
@FieldNameConstants
public class AddressCreateEditDTO {
    /*UUID id;*/
    User userid;
    String street;
    String house;
    String apartment;
}

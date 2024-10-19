package com.example.education.dto.address;

import com.example.education.entity.User;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

@Value
@FieldNameConstants
public class AddressCreateEditDTO {
    User userid;
    String street;
    String house;
    String apartment;
}

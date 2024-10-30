package com.example.education.dto.address;

import com.example.education.entity.UserEntity;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
public class AddressCreateEditDTO {
    UserEntity userid;
    String street;
    String house;
    String apartment;
}

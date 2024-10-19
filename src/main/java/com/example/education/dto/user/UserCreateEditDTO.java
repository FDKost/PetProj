package com.example.education.dto.user;

import com.example.education.entity.Role;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

@Value
@FieldNameConstants
public class UserCreateEditDTO {
    String login;
    String password;
    Role role;
}

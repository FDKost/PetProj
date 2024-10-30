package com.example.education.dto.user;

import com.example.education.entity.Role;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
public class UserCreateEditDTO {
    String login;
    String password;
    Role role;
}

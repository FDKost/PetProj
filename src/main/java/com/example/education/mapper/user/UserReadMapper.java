package com.example.education.mapper.user;

import com.example.education.dto.user.UserReadDTO;
import com.example.education.entity.UserEntity;
import com.example.education.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class UserReadMapper implements Mapper<UserEntity, UserReadDTO> {
    @Override
    public UserReadDTO map(UserEntity object) {
        return new UserReadDTO(
                object.getId(),
                object.getLogin(),
                object.getPassword(),
                object.getRole());
    }
}

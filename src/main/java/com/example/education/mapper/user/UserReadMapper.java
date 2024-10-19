package com.example.education.mapper.user;

import com.example.education.dto.user.UserReadDTO;
import com.example.education.entity.User;
import com.example.education.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class UserReadMapper implements Mapper<User, UserReadDTO> {
    @Override
    public UserReadDTO map(User object) {
        return new UserReadDTO(
                object.getId(),
                object.getLogin(),
                object.getPassword(),
                object.getRole());
    }
}

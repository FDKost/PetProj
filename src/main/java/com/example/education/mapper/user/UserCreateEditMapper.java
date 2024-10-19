package com.example.education.mapper.user;

import com.example.education.dto.user.UserCreateEditDTO;
import com.example.education.entity.User;
import com.example.education.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class UserCreateEditMapper implements Mapper<UserCreateEditDTO, User> {
    @Override
    public User map(UserCreateEditDTO object) {
        User user = new User();
        copy(object, user);
        return user;
    }

    @Override
    public User map(UserCreateEditDTO fromObject, User toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(UserCreateEditDTO object, User user) {
        user.setLogin(object.getLogin());
        user.setPassword(object.getPassword());
        user.setRole(object.getRole());
    }
}

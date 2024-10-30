package com.example.education.mapper.user;

import com.example.education.dto.user.UserCreateEditDTO;
import com.example.education.entity.UserEntity;
import com.example.education.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class UserCreateEditMapper implements Mapper<UserCreateEditDTO, UserEntity> {
    @Override
    public UserEntity map(UserCreateEditDTO object) {
        UserEntity user = new UserEntity();
        fillUserCreateEditDTO(object, user);
        return user;
    }

    @Override
    public UserEntity map(UserCreateEditDTO fromObject, UserEntity toObject) {
        fillUserCreateEditDTO(fromObject, toObject);
        return toObject;
    }

    private void fillUserCreateEditDTO(UserCreateEditDTO object, UserEntity user) {
        user.setLogin(object.getLogin());
        user.setPassword(object.getPassword());
        user.setRole(object.getRole());
    }
}

package com.example.education.services.user;

import com.example.education.dto.user.UserCreateEditDTO;
import com.example.education.dto.user.UserReadDTO;

import java.util.Optional;
import java.util.UUID;

public interface UserService {

    Optional<UserReadDTO> findById(UUID id);

    Optional<UserReadDTO> findByUsername(String username);

    UserReadDTO create(UserCreateEditDTO userDTO);

    Optional<UserReadDTO> update(UUID id, UserCreateEditDTO userDTO);

    Boolean delete(UUID id);

}

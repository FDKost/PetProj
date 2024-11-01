package com.example.education.services.user;

import com.example.education.dto.address.AddressCreateEditDTO;
import com.example.education.dto.user.UserCreateEditDTO;
import com.example.education.dto.user.UserReadDTO;
import com.example.education.entity.UserEntity;
import com.example.education.mapper.user.UserCreateEditMapper;
import com.example.education.mapper.user.UserReadMapper;
import com.example.education.repositories.UserRepository;
import com.example.education.services.address.AddressService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateEditMapper userCreateEditMapper;
    @Qualifier("addressServiceImpl")
    private final AddressService addressService;
    @Qualifier("userServiceImpl")
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           UserReadMapper userReadMapper,
                           UserCreateEditMapper userCreateEditMapper,
                           @Lazy AddressService addressService,
                           @Lazy UserService userService,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userReadMapper = userReadMapper;
        this.userCreateEditMapper = userCreateEditMapper;
        this.addressService = addressService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<UserReadDTO> findById(UUID id){
        return userRepository.findById(id)
                .map(userReadMapper::map);
    }

    @Override
    public Optional<UserReadDTO> findByUsername(String username){
        return userRepository.findByLogin(username)
                .map(userReadMapper::map);
    }

    @Override
    public UserReadDTO create(UserCreateEditDTO userDTO){
        return Optional.of(userDTO)
                .map(userCreateEditMapper::map)
                .map(userRepository::save)
                .map(userReadMapper::map)
                .orElseThrow();
    }

    @Override
    @Transactional
    public Optional<UserReadDTO> update(UUID id, UserCreateEditDTO userDTO){
        return userRepository.findById(id)
                .map(entity -> userCreateEditMapper.map(userDTO, entity))
                .map(userRepository::saveAndFlush)
                .map(userReadMapper::map);
    }

    @Override
    @Transactional
    public Boolean delete(UUID id){
        return userRepository.findById(id)
                .map(entity -> {
                    userRepository.delete(entity);
                    userRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    @Transactional
    public void fillCreateUser(UserCreateEditDTO userCreateEditDTO,
                               AddressCreateEditDTO addressCreateEditDTO){
        userCreateEditDTO.setPassword(passwordEncoder.encode(userCreateEditDTO.getPassword()));
        userService.create(userCreateEditDTO);
        Optional<UserReadDTO> user = userService.findByUsername(userCreateEditDTO.getLogin());
        user.ifPresent(userReadDTO ->
                addressCreateEditDTO.setUserid(new UserEntity(
                        userReadDTO.getId(),
                        userCreateEditDTO.getLogin(),
                        userCreateEditDTO.getPassword(),
                        userCreateEditDTO.getRole()
                )));
        addressService.create(addressCreateEditDTO);
    }
}

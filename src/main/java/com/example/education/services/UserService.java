package com.example.education.services;

import com.example.education.dto.user.UserCreateEditDTO;
import com.example.education.dto.user.UserReadDTO;
import com.example.education.mapper.user.UserCreateEditMapper;
import com.example.education.mapper.user.UserReadMapper;
import com.example.education.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserCreateEditMapper userCreateEditMapper;

    public Optional<UserReadDTO> findById(UUID id){
        return userRepository.findById(id)
                .map(userReadMapper::map);
    }

    @Transactional
    public UserReadDTO create(UserCreateEditDTO userDTO){
        return Optional.of(userDTO)
                .map(userCreateEditMapper::map)
                .map(userRepository::save)
                .map(userReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<UserReadDTO> update(UUID id, UserCreateEditDTO userDTO){
        return userRepository.findById(id)
                .map(entity -> userCreateEditMapper.map(userDTO, entity))
                .map(userRepository::saveAndFlush)
                .map(userReadMapper::map);
    }

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username)
                .map(user -> new User(
                        user.getLogin(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user: "+ username));
    }
}

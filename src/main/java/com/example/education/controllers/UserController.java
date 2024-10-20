package com.example.education.controllers;

import com.example.education.dto.user.UserCreateEditDTO;
import com.example.education.dto.user.UserReadDTO;
import com.example.education.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/registration")
    public ModelAndView createUser(@RequestBody UserCreateEditDTO userCreateEditDTO) {
        userService.create(userCreateEditDTO);
        String loginUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/login").toUriString();
        return new ModelAndView("redirect:" + loginUrl);
    }

    @GetMapping("/api/readUser")
    public Optional<UserReadDTO> readUser(@RequestParam UUID id){
        return userService.findById(id);
    }

    @PutMapping("/api/editUser")
    public void editUser(@RequestParam UUID userId, UserCreateEditDTO userCreateEditDTO) {
        userService.update(userId, userCreateEditDTO);
    }

    @DeleteMapping("/api/deleteUser")
    public void deleteUser(@RequestParam UUID id){
        userService.delete(id);
    }

    @GetMapping("/api/readLogin")
    public Optional<UserReadDTO> findByLogin(@RequestParam String login){
        return userService.findByUsername(login);
    }
}

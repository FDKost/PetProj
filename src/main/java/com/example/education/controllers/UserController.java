package com.example.education.controllers;

import com.example.education.dto.address.AddressCreateEditDTO;
import com.example.education.dto.user.UserCreateEditDTO;
import com.example.education.dto.user.UserReadDTO;
import com.example.education.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/registration")
    public ModelAndView createUser(UserCreateEditDTO userCreateEditDTO,
                                   AddressCreateEditDTO addressCreateEditDTO) {
        userService.fillCreateUser(userCreateEditDTO, addressCreateEditDTO);

        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/api/readUser")
    public Optional<UserReadDTO> readUser(@RequestParam UUID id) {
        return userService.findById(id);
    }

    @PutMapping("/api/editUser")
    public void editUser(@RequestParam UUID userId, UserCreateEditDTO userCreateEditDTO) {
        userService.update(userId, userCreateEditDTO);
    }

    @DeleteMapping("/api/deleteUser")
    public void deleteUser(@RequestParam UUID id) {
        userService.delete(id);
    }

    @GetMapping("/api/readLogin")
    public Optional<UserReadDTO> findByLogin(@RequestParam String login) {
        return userService.findByUsername(login);
    }
}

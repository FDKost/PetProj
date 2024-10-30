package com.example.education.controllers;

import com.example.education.dto.address.AddressCreateEditDTO;
import com.example.education.dto.user.UserCreateEditDTO;
import com.example.education.dto.user.UserReadDTO;
import com.example.education.entity.UserEntity;
import com.example.education.services.address.AddressServiceImpl;
import com.example.education.services.user.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserServiceImpl userService;
    private final AddressServiceImpl addressService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/registration")
    public ModelAndView createUser(UserCreateEditDTO userCreateEditDTO,
                                   AddressCreateEditDTO addressCreateEditDTO) {
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
        return new ModelAndView("redirect:/login");
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

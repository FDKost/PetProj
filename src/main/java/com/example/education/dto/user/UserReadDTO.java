package com.example.education.dto.user;

import com.example.education.entity.Role;
import lombok.Value;
import org.springframework.ui.Model;

import java.util.UUID;

@Value
public class UserReadDTO {
    UUID id;
    String login;
    String password;
    Role role;

    public Model populateUserModel(Model model) {
        model.addAttribute("userId", id);
        return model;
    }
}

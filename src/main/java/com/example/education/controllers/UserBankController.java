package com.example.education.controllers;

import com.example.education.dto.userbank.UserBankCreateEditDTO;
import com.example.education.dto.userbank.UserBankReadDTO;
import com.example.education.services.userbank.UserBankService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserBankController {
    private final UserBankService userBankService;

    @PostMapping("/api/userBank_create")
    public ModelAndView createUserBank(UserBankCreateEditDTO userBankCreateEditDTO) {
        userBankService.create(userBankCreateEditDTO);

        return new ModelAndView("redirect:/order");
    }

    @GetMapping("/api/userBank_read")
    public Optional<UserBankReadDTO> readUserBank(@RequestParam UUID userId) {
        return userBankService.findByUserId(userId);
    }

    @PostMapping("/api/userBank_edit")
    public ModelAndView editUserBank(UserBankCreateEditDTO userBankCreateEditDTO,
                                     @RequestParam UUID userId) {
        try {
            userBankService.update(userId, userBankCreateEditDTO);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ModelAndView("redirect:/order");
    }

    @DeleteMapping("/api/userBank_delete")
    public void deleteUserBank(@RequestParam UUID userId) {
        userBankService.delete(userId);
    }
}

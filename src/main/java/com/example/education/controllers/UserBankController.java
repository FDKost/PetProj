package com.example.education.controllers;

import com.example.education.dto.userbank.UserBankCreateEditDTO;
import com.example.education.dto.userbank.UserBankReadDTO;
import com.example.education.services.userbank.UserBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;
import java.util.UUID;

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
        userBankService.update(userId,userBankCreateEditDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return new ModelAndView("redirect:/order");
    }

    @DeleteMapping("/api/userBank_delete")
    public void deleteUserBank(@RequestParam UUID userId) {
        userBankService.delete(userId);
    }
}

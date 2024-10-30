package com.example.education.controllers;

import com.example.education.dto.user.UserReadDTO;
import com.example.education.dto.userbank.UserBankCreateEditDTO;
import com.example.education.dto.userbank.UserBankReadDTO;
import com.example.education.entity.BankEntity;
import com.example.education.entity.UserEntity;
import com.example.education.services.bank.BankServiceImpl;
import com.example.education.services.user.UserServiceImpl;
import com.example.education.services.userbank.UserBankServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final UserServiceImpl userService;
    private final BankServiceImpl bankService;
    private final UserBankServiceImpl userBankService;

    @GetMapping
    public String profile(@AuthenticationPrincipal UserDetails userDetails, Model model){
        String login = userDetails.getUsername();
        Optional<UserReadDTO> user = userService.findByUsername(login);
        String arasakaName = "Arasaka";
        String militechName = "Militech";
        Optional<BankEntity> arasakaBank = bankService.findBankEntityByBankName(arasakaName);
        Optional<BankEntity> militechBank = bankService.findBankEntityByBankName(militechName);
        if(user.isPresent()) {
            UserEntity actualUser = new UserEntity(user.get().getId(),
                    user.get().getLogin(),
                    user.get().getPassword(),
                    user.get().getRole());
            model.addAttribute("userId",actualUser.getId());

            Optional<UserBankReadDTO> userBank = userBankService.findByUserId(user.get().getId());
            if(userBank.isPresent()) {
                BankEntity actualBank = new BankEntity(userBank.get().getBank().getId(),
                        userBank.get().getBank().getBankName(),
                        userBank.get().getBank().getBankCode());
                model.addAttribute("bankId",actualBank.getId());
            }

            if (arasakaBank.isPresent()) {
                BankEntity arasaka = new BankEntity(arasakaBank.get().getId(),
                        arasakaBank.get().getBankName(),
                        arasakaBank.get().getBankCode());
                model.addAttribute("Arasaka",arasaka.getId());
                if (userBankService.findByUserId(user.get().getId()).isPresent()) {
                    model.addAttribute("userBank", userBankService.findByUserId(user.get().getId()));
                } else {
                    model.addAttribute("userBank", userBankService.create(new UserBankCreateEditDTO(
                            new UserEntity(user.get().getId(),
                                    user.get().getLogin(),
                                    user.get().getPassword(),
                                    user.get().getRole()
                            ),
                            arasakaBank.get()
                    )));
                }
            }
        }

        if(militechBank.isPresent()) {
            BankEntity militech = new BankEntity(militechBank.get().getId(),
                    militechBank.get().getBankName(),
                    militechBank.get().getBankCode());
            model.addAttribute("Militech",militech.getId());
        }
        return "profile";
    }
}

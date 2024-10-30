package com.example.education.controllers;

import com.example.education.dto.address.AddressCreateEditDTO;
import com.example.education.dto.address.AddressReadDTO;
import com.example.education.dto.user.UserReadDTO;
import com.example.education.services.address.AddressServiceImpl;
import com.example.education.services.user.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class AddressController {
    private final AddressServiceImpl addressService;
    private final UserServiceImpl userService;

    @GetMapping("/profile/findAll")
    public List<AddressReadDTO> findAll(Model model){
        List<AddressReadDTO> allAddresses = addressService.findAll();
        model.addAttribute("addresses", addressService.findAll());
        return allAddresses;
    }

    @PostMapping("/profile/create")
    public ModelAndView createAddress(AddressCreateEditDTO addressCreateEditDTO,
                                      @AuthenticationPrincipal UserDetails userDetails){
        Optional<UserReadDTO> user = userService.findByUsername(userDetails.getUsername());
        if (user.isPresent()){
            Optional<AddressReadDTO> existingAddress = addressService.findAddressByUserId(user.get().getId());

            if (existingAddress.isPresent()) {
                addressService.update(existingAddress.get().getId(), addressCreateEditDTO);
            }else{
                addressService.create(addressCreateEditDTO);
            }
        }


        return new ModelAndView("redirect:/order");
    }

    @GetMapping("/profile/read")
    public Optional<AddressReadDTO> readAddress(@RequestParam UUID addressId){
        return addressService.findById(addressId);
    }
    @PutMapping("/profile/edit")
    public Optional<AddressReadDTO> editAddress(AddressCreateEditDTO addressCreateEditDTO,AddressReadDTO addressReadDTO){
        return addressService.update(addressReadDTO.getId(), addressCreateEditDTO);
    }
    @DeleteMapping("/profile/delete")
    public void deleteAddress(@RequestParam UUID addressId){
        addressService.delete(addressId);
    }
}

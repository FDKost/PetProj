package com.example.education.controllers;

import com.example.education.dto.address.AddressCreateEditDTO;
import com.example.education.dto.address.AddressReadDTO;
import com.example.education.services.address.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @GetMapping("/profile/findAll")
    public List<AddressReadDTO> findAll(Model model) {
        List<AddressReadDTO> allAddresses = addressService.findAll();
        model.addAttribute("addresses", addressService.findAll());
        return allAddresses;
    }

    @PostMapping("/profile/create")
    public ModelAndView createAddress(AddressCreateEditDTO addressCreateEditDTO) {
        addressService.fillCreateAddress(addressCreateEditDTO);

        return new ModelAndView("redirect:/order");
    }

    @GetMapping("/profile/read")
    public Optional<AddressReadDTO> readAddress(@RequestParam UUID addressId) {
        return addressService.findById(addressId);
    }

    @PutMapping("/profile/edit")
    public Optional<AddressReadDTO> editAddress(AddressCreateEditDTO addressCreateEditDTO, AddressReadDTO addressReadDTO) {
        return addressService.update(addressReadDTO.getId(), addressCreateEditDTO);
    }

    @DeleteMapping("/profile/delete")
    public void deleteAddress(@RequestParam UUID addressId) {
        addressService.delete(addressId);
    }
}

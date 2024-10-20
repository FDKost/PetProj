package com.example.education.controllers;

import com.example.education.dto.address.AddressCreateEditDTO;
import com.example.education.dto.address.AddressReadDTO;
import com.example.education.dto.user.UserReadDTO;
import com.example.education.entity.User;
import com.example.education.services.AddressService;
import com.example.education.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@AllArgsConstructor
public class AddressController {
    private final AddressService addressService;
    private final UserService userService;

    /*@PostMapping("/profile/create")
    public ModelAndView createAddress(Address address, @AuthenticationPrincipal UserDetails userDetails, Model model){
        String orderUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/order").toUriString();
        String username = userDetails.getUsername();

        // Найти пользователя по его имени
        User user = userDao.getUserByLogin(username);

        // Проверить, существует ли уже адрес для данного пользователя
        Address existingAddress = addressDao.getAddressByUserId(user.getId());
        if(existingAddress != null) {
            // Если адрес уже существует, обновить его данные
            address.setId(existingAddress.getId());
            addressDao.editAddress(address);
        } else {
            // Если адрес не существует, создать новый адрес
            addressDao.createAddress(address);
        }

        return new ModelAndView("redirect:" + orderUrl);
    }*/
    @GetMapping("/profile/findAll")
    public List<AddressReadDTO> findAll(Model model){
        List<AddressReadDTO> allAddresses = addressService.findAll();
        model.addAttribute("addresses", addressService.findAll());
        return allAddresses;
    }

    @PostMapping("/profile/create")
    public ModelAndView createAddress(AddressCreateEditDTO addressCreateEditDTO,
                                      UserReadDTO userReadDTO,
                                      @AuthenticationPrincipal UserDetails userDetails){
        String orderUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/order").toUriString();

        Optional<AddressReadDTO> existingAddress = addressService.findAddressByUserId(userService.findByUsername(userDetails.getUsername()).get().getId());

        if (existingAddress.isPresent()) {
            addressService.update(existingAddress.get().getId(), addressCreateEditDTO);
        }else{
            addressService.create(addressCreateEditDTO);
        }

        return new ModelAndView("redirect:" + orderUrl);
    }

    @GetMapping("/profile/read")
    public Optional<AddressReadDTO> readAddress(@RequestParam UUID addressId){
        return addressService.findById(addressId);
    }
    @PutMapping("/profile/edit")
    public Optional<AddressReadDTO> editAddress(AddressCreateEditDTO addressCreateEditDTO,AddressReadDTO addressReadDTO){
        return addressService.update(addressReadDTO.getId(), addressCreateEditDTO);
        /*addressDao.editAddress(address);*/
    }
    @DeleteMapping("/profile/delete")
    public void deleteAddress(@RequestParam UUID addressId){
        addressService.delete(addressId);
    }
}

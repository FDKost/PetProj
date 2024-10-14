package com.example.education.controllers.restControllers;

import com.example.education.entity.Address;
import com.example.education.dao.AddressDao;
import com.example.education.dao.UserDao;
import com.example.education.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@AllArgsConstructor
public class AddressController {
    private final AddressDao addressDao;
    private final UserDao userDao;

    @PostMapping("/profile/create")
    public ModelAndView createAddress(Address address, @AuthenticationPrincipal UserDetails userDetails, Model model){
        String orderUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/order").toUriString();
        String username = userDetails.getUsername();

        // Найти пользователя по его имени
        User user = userDao.getUserByLogin(username);
        address.setUserId(user.getId().intValue());

        // Проверить, существует ли уже адрес для данного пользователя
        Address existingAddress = addressDao.getAddressByUserId(user.getId().intValue());
        if(existingAddress != null) {
            // Если адрес уже существует, обновить его данные
            address.setAddressId(existingAddress.getAddressId());
            addressDao.editAddress(address);
        } else {
            // Если адрес не существует, создать новый адрес
            addressDao.createAddress(address);
        }

        return new ModelAndView("redirect:" + orderUrl);
    }

    @GetMapping("/profile/read")
    public Address readAddress(@RequestParam Long addressId){
        return addressDao.getAddressById(addressId);
    }
    @PutMapping("/profile/edit")
    public void editAddress(@RequestBody Address address){
        addressDao.editAddress(address);
    }
    @DeleteMapping("/profile/delete")
    public void deleteAddress(@RequestParam long addressId){
        addressDao.deleteAddress(addressId);
    }
}

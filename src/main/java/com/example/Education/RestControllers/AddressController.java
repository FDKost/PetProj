package com.example.Education.RestControllers;

import com.example.Education.Address;
import com.example.Education.DAO.AddressDao;
import com.example.Education.DAO.UserDao;
import com.example.Education.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@AllArgsConstructor
public class AddressController {
    private final AddressDao dao;
    private final UserDao userDao;

    @PostMapping("/profile/create")
    public ModelAndView createAddress(Address address, @AuthenticationPrincipal UserDetails userDetails){
        String orderUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/order").toUriString();
        String username = userDetails.getUsername();

        // Найти пользователя по его имени
        User user = userDao.getUserByLogin(username);
        address.setId_user(user.getId().intValue());
        dao.createAddress(address);
        return new ModelAndView("redirect:" + orderUrl);
    }
    @GetMapping("/profile/read")
    public Address readAddress(@RequestParam Long id_address){
        return dao.getAddressById(id_address);
    }
    @PutMapping("/profile")
    public void editAddress(@RequestBody Address address){
        dao.editAddress(address);
    }
    @DeleteMapping("/profile")
    public void deleteAddress(@RequestParam long id_address){
        dao.deleteAddress(id_address);
    }
}

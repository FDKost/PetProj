/*
package com.example.Education.Controllers;

import com.example.Education.Address;
import com.example.Education.DAO.AddressDao;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
public class AddressController {
    private final AddressDao dao;

    @PostMapping
    public Long createAddress(@RequestBody Address address){
        return dao.createAddress(address);
    }
    @GetMapping
    public Address readAddress(@RequestParam Long id_address){
        return dao.getAddressById(id_address);
    }
    @PutMapping
    public void editAddress(@RequestBody Address address){
        dao.editAddress(address);
    }
    @DeleteMapping
    public void deleteAddress(@RequestParam long id_address){
        dao.deleteAddress(id_address);
    }
}
*/

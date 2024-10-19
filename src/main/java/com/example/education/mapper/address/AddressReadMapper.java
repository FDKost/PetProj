package com.example.education.mapper.address;

import com.example.education.dto.address.AddressReadDTO;
import com.example.education.entity.Address;
import com.example.education.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AddressReadMapper implements Mapper<Address,AddressReadDTO> {
    @Override
    public AddressReadDTO map(Address object) {
        return new AddressReadDTO(
                object.getId(),
                object.getUser(),
                object.getStreet(),
                object.getHouse(),
                object.getApartment());
    }
}

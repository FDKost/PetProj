package com.example.education.mapper.address;

import com.example.education.dto.address.AddressReadDTO;
import com.example.education.entity.AddressEntity;
import com.example.education.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AddressReadMapper implements Mapper<AddressEntity, AddressReadDTO> {
    @Override
    public AddressReadDTO map(AddressEntity object) {
        return new AddressReadDTO(
                object.getId(),
                object.getUser(),
                object.getStreet(),
                object.getHouse(),
                object.getApartment());
    }
}

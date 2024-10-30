package com.example.education.mapper.address;

import com.example.education.dto.address.AddressCreateEditDTO;
import com.example.education.entity.AddressEntity;
import com.example.education.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AddressCreateEditMapper implements Mapper<AddressCreateEditDTO, AddressEntity> {
    @Override
    public AddressEntity map(AddressCreateEditDTO object) {
        AddressEntity address = new AddressEntity();
        fillAddressCreateEditDTO(object, address);
        return address;
    }

    @Override
    public AddressEntity map(AddressCreateEditDTO fromObject, AddressEntity toObject) {
        fillAddressCreateEditDTO(fromObject, toObject);
        return toObject;
    }

    private void fillAddressCreateEditDTO(AddressCreateEditDTO object, AddressEntity address) {
        address.setUser(object.getUserid());
        address.setStreet(object.getStreet());
        address.setHouse(object.getHouse());
        address.setApartment(object.getApartment());
    }

}

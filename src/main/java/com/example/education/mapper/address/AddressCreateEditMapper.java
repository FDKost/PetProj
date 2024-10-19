package com.example.education.mapper.address;

import com.example.education.dto.address.AddressCreateEditDTO;
import com.example.education.entity.Address;
import com.example.education.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AddressCreateEditMapper implements Mapper<AddressCreateEditDTO, Address> {
    @Override
    public Address map(AddressCreateEditDTO object) {
        Address address = new Address();
        copy(object, address);
        return address;
    }

    @Override
    public Address map(AddressCreateEditDTO fromObject, Address toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(AddressCreateEditDTO object, Address address) {
        address.setUser(object.getUserid());
        address.setStreet(object.getStreet());
        address.setHouse(object.getHouse());
        address.setApartment(object.getApartment());
    }
}

package com.example.education.services.address;

import com.example.education.dto.address.AddressCreateEditDTO;
import com.example.education.dto.address.AddressReadDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AddressService {
    Optional<AddressReadDTO> findById(UUID id);

    List<AddressReadDTO> findAll();

    Optional<AddressReadDTO> findAddressByUserId(UUID userId);

    AddressReadDTO create(AddressCreateEditDTO addressDTO);

    Optional<AddressReadDTO> update(UUID id, AddressCreateEditDTO addressDTO);

    boolean delete(UUID id);

    void fillCreateAddress(AddressCreateEditDTO addressCreateEditDTO);
}

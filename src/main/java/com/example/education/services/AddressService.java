package com.example.education.services;

import com.example.education.dto.address.AddressCreateEditDTO;
import com.example.education.dto.address.AddressReadDTO;
import com.example.education.mapper.address.AddressCreateEditMapper;
import com.example.education.mapper.address.AddressReadMapper;
import com.example.education.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressCreateEditMapper addressCreateEditMapper;
    private final AddressReadMapper addressReadMapper;

    public Optional<AddressReadDTO> findById(UUID id){
        return addressRepository.findById(id)
                .map(addressReadMapper::map);
    }

    public Optional<AddressReadDTO> findAddressByUserId(UUID userId){
        return addressRepository.findAddressByUserId(userId)
                .map(addressReadMapper::map);
    }

    @Transactional
    public AddressReadDTO create(AddressCreateEditDTO addressDTO){
        return Optional.of(addressDTO)
                .map(addressCreateEditMapper::map)
                .map(addressRepository::save)
                .map(addressReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<AddressReadDTO> update(UUID id, AddressCreateEditDTO addressDTO){
        return addressRepository.findById(id)
                .map(entity -> addressCreateEditMapper.map(addressDTO,entity))
                .map(addressRepository::saveAndFlush)
                .map(addressReadMapper::map);
    }

    @Transactional
    public boolean delete(UUID id){
        return addressRepository.findById(id)
                .map(entity -> {
                    addressRepository.delete(entity);
                    addressRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}

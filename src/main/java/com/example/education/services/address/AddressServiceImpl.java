package com.example.education.services.address;

import com.example.education.dto.address.AddressCreateEditDTO;
import com.example.education.dto.address.AddressReadDTO;
import com.example.education.mapper.address.AddressCreateEditMapper;
import com.example.education.mapper.address.AddressReadMapper;
import com.example.education.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressCreateEditMapper addressCreateEditMapper;
    private final AddressReadMapper addressReadMapper;

    @Override
    public Optional<AddressReadDTO> findById(UUID id) {
        return addressRepository.findById(id)
                .map(addressReadMapper::map);
    }

    @Override
    public List<AddressReadDTO> findAll() {
        return addressRepository.findAll().stream()
                .map(addressReadMapper::map)
                .toList();
    }

    @Override
    public Optional<AddressReadDTO> findAddressByUserId(UUID userId) {
        return addressRepository.findAddressByUserId(userId)
                .map(addressReadMapper::map);
    }

    @Override
    public AddressReadDTO create(AddressCreateEditDTO addressDTO) {
        return Optional.of(addressDTO)
                .map(addressCreateEditMapper::map)
                .map(addressRepository::save)
                .map(addressReadMapper::map)
                .orElseThrow();
    }

    @Override
    public Optional<AddressReadDTO> update(UUID id, AddressCreateEditDTO addressDTO) {
        return addressRepository.findById(id)
                .map(entity -> addressCreateEditMapper.map(addressDTO, entity))
                .map(addressRepository::saveAndFlush)
                .map(addressReadMapper::map);
    }

    @Override
    public boolean delete(UUID id) {
        return addressRepository.findById(id)
                .map(entity -> {
                    addressRepository.delete(entity);
                    addressRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    public void fillCreateAddress(AddressCreateEditDTO addressCreateEditDTO) {
        Optional<AddressReadDTO> existingAddress = findAddressByUserId(addressCreateEditDTO.getUserid().getId());

        if (existingAddress.isPresent()) {
            update(existingAddress.get().getId(), addressCreateEditDTO);
        } else {
            create(addressCreateEditDTO);
        }
    }
}

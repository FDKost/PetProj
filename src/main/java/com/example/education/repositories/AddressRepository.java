package com.example.education.repositories;

import com.example.education.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {

    Optional<Address> findAddressById(UUID id);

    Optional<Address> findAddressByUserId(UUID id);
}

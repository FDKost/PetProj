package com.example.education.repositories;

import com.example.education.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {

    Optional<Address> findAddressById(UUID id);

    Optional<Address> findAddressByUserId(UUID id);
}

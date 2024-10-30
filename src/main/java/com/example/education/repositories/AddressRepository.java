package com.example.education.repositories;

import com.example.education.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, UUID> {

    Optional<AddressEntity> findAddressById(UUID id);

    Optional<AddressEntity> findAddressByUserId(UUID id);
}

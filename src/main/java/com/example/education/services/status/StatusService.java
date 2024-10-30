package com.example.education.services.status;

import com.example.education.entity.StatusEntity;

import java.util.Optional;

public interface StatusService {
    Optional<StatusEntity> findByDescription(String description);
}

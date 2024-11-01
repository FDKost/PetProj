package com.example.education.services.status;

import com.example.education.entity.StatusEntity;
import com.example.education.repositories.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StatusServiceImpl implements StatusService {
    private final StatusRepository statusRepository;

    @Override
    public Optional<StatusEntity> findByDescription(String description) {
        return statusRepository.findByDescription(description);
    }
}

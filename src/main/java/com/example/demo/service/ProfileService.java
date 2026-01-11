package com.example.demo.service;

import com.example.demo.dto.ProfileDTO;
import com.example.demo.entity.DivisionEntity;
import com.example.demo.entity.UserProfileEntity;
import com.example.demo.repository.ProfileRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public ProfileDTO createProfile(UserProfileEntity userProfileEntity) {

        UserProfileEntity saved = profileRepository.save(userProfileEntity);

        return new ProfileDTO(
                saved.getId(),
                saved.getFirstName(),
                saved.getSecondName(),
                saved.getPhoneNumber(),
                saved.getBirdthay(),
                saved.getDivisions().stream()
                        .map(DivisionEntity::getId)
                        .collect(java.util.stream.Collectors.toSet()),
                saved.getRole(),
                saved.getPosition()
        );
    }

    public List<ProfileDTO> getAllProfiles() {
        return profileRepository.findAllByDivisions().stream()
                .map(this::toDomainDTO)
                .toList();
    }


    public ProfileDTO toDomainDTO(UserProfileEntity userProfileEntity) {
        return new ProfileDTO(
                userProfileEntity.getId(),
                userProfileEntity.getFirstName(),
                userProfileEntity.getSecondName(),
                userProfileEntity.getPhoneNumber(),
                userProfileEntity.getBirdthay(),
                userProfileEntity.getDivisions().stream()
                        .map(DivisionEntity::getId)
                        .collect(Collectors.toSet()),
                userProfileEntity.getRole(),
                userProfileEntity.getPosition()
        );
    }
}

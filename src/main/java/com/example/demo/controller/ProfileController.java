package com.example.demo.controller;

import com.example.demo.dto.ProfileDTO;
import com.example.demo.entity.UserProfileEntity;
import com.example.demo.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profile/")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public ResponseEntity<ProfileDTO> create(@Valid @RequestBody UserProfileEntity userProfileEntity) {
        ProfileDTO dto = profileService.createProfile(userProfileEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping
    public List<ProfileDTO> getAll() {
        List<ProfileDTO> dto = profileService.getAllProfiles();
        return dto;
    }
}

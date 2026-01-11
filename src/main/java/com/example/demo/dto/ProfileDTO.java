package com.example.demo.dto;


import com.example.demo.entity.PlayerPosition;
import com.example.demo.entity.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public record ProfileDTO(
        UUID id,
        String firstName,
        String secondName,
        String phoneNumber,
        LocalDateTime birdthay,
        Set<UUID> divisionsIds,
        Role roles,
        PlayerPosition positions
) {
}

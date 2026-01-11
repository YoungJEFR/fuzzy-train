package com.example.demo.dto;

import com.example.demo.entity.ReservationStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReservationDTO(
        UUID id,
        UUID trainingId,
        UUID userId,
        ReservationStatus status,
        LocalDateTime createdAt
) {}
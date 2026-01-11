package com.example.demo.dto;

import java.util.UUID;

public record ReservationCreateRequest(
        UUID trainingId,
        UUID profileId
) {}
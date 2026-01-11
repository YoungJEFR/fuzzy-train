package com.example.demo.dto;

import java.util.UUID;

public record MatchPlayerDTO(
        UUID userId,
        String firstName,
        String secondName
) {}

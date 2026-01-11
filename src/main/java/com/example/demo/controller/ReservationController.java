package com.example.demo.controller;

import com.example.demo.dto.MatchPlayerDTO;
import com.example.demo.dto.ReservationCreateRequest;
import com.example.demo.dto.ReservationDTO;
import com.example.demo.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/training-reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<ReservationDTO> create(@RequestBody ReservationCreateRequest req) {
        ReservationDTO dto = reservationService.createReservation(req.trainingId(), req.profileId());
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @DeleteMapping("/training/{trainingId}/profile/{profileId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID trainingId, @PathVariable UUID profileId) {
        reservationService.deleteReservation(trainingId, profileId);
    }

    @GetMapping("/training/{trainingId}/players")
    public List<MatchPlayerDTO> getPlayers(@PathVariable UUID trainingId) {
        return reservationService.getPlayersByTraining(trainingId);
    }
}

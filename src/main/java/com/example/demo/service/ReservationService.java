package com.example.demo.service;

import com.example.demo.dto.MatchPlayerDTO;
import com.example.demo.dto.ReservationDTO;
import com.example.demo.entity.ReservationEntity;
import com.example.demo.entity.TrainingEntity;
import com.example.demo.repository.ProfileRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.TrainingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class ReservationService {

    private final ProfileRepository profileRepository;
    private final ReservationRepository reservationRepository;
    private final TrainingRepository trainingRepository;

    private final Logger log = LoggerFactory.getLogger(ReservationService.class);

    public ReservationService(ProfileRepository profileRepository,
                              ReservationRepository reservationRepository,
                              TrainingRepository trainingRepository) {
        this.profileRepository = profileRepository;
        this.reservationRepository = reservationRepository;
        this.trainingRepository = trainingRepository;
    }

    @Transactional
    public ReservationDTO createReservation(UUID trainingId, UUID profileId) {

        if (reservationRepository.existsByTraining_IdAndUser_Id(trainingId, profileId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Reservation already exists");
        }

        var profileEntity = profileRepository.findById(profileId)
                .orElseThrow(() -> new EntityNotFoundException("Profile with id " + profileId + " not found"));

        TrainingEntity trainingEntity = trainingRepository.findById(trainingId)
                .orElseThrow(() -> new EntityNotFoundException("Training with id " + trainingId + " not found"));

        var reservationEntity = new ReservationEntity();
        reservationEntity.setTraining(trainingEntity);
        reservationEntity.setUser(profileEntity);

        ReservationEntity saved = reservationRepository.save(reservationEntity);
        return toDomainDTO(saved);
    }

    @Transactional
    public void deleteReservation(UUID trainingId, UUID profileId) {

        ReservationEntity reservation = reservationRepository
                .findByTraining_IdAndUser_Id(trainingId, profileId)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found"));

        reservationRepository.delete(reservation);

        log.info("Reservation deleted. trainingId={}, profileId={}", trainingId, profileId);
    }

    public List<MatchPlayerDTO> getPlayersByTraining(UUID trainingId) {
        return reservationRepository.findPlayersByTraining(trainingId);
    }

    public ReservationDTO toDomainDTO(ReservationEntity reservationEntity) {
        return new ReservationDTO(
                reservationEntity.getId(),
                reservationEntity.getTraining().getId(),
                reservationEntity.getUser().getId(),
                reservationEntity.getStatus(),
                reservationEntity.getCreatedAt()
        );
    }
}

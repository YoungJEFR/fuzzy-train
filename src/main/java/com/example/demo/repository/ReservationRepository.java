package com.example.demo.repository;

import com.example.demo.dto.MatchPlayerDTO;
import com.example.demo.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<ReservationEntity, UUID> {

    boolean existsByTraining_IdAndUser_Id(UUID trainingId, UUID userId);

    Optional<ReservationEntity> findByTraining_IdAndUser_Id(UUID trainingId, UUID userId);

    @Query("""
        select new com.example.demo.dto.MatchPlayerDTO(
            u.id,
            u.firstName,
            u.secondName
        )
        from ReservationEntity r
        join r.user u
        where r.training.id = :trainingId
          and r.status = com.example.demo.entity.ReservationStatus.ACTIVE
    """)
    List<MatchPlayerDTO> findPlayersByTraining(@Param("trainingId") UUID trainingId);
}

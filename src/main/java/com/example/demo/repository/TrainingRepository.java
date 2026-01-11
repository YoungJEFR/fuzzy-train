package com.example.demo.repository;

import com.example.demo.entity.TrainingEntity;
import com.example.demo.entity.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TrainingRepository extends JpaRepository<TrainingEntity, UUID> {
}

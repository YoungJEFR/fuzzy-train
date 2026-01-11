package com.example.demo.repository;

import com.example.demo.entity.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProfileRepository extends JpaRepository<UserProfileEntity, UUID> {

    @Query("""
        select distinct p
        from UserProfileEntity p
        left join fetch p.divisions
    """)
    List<UserProfileEntity> findAllByDivisions();
}

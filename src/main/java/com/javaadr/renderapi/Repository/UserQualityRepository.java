package com.javaadr.renderapi.Repository;

import com.javaadr.renderapi.Entity.UserQuality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserQualityRepository extends JpaRepository<UserQuality, UUID> {

    Optional<UserQuality> findByUserIdAndQualityId(UUID userId, Integer qualityId);
}

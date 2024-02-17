package com.javaadr.renderapi.Service;

import com.javaadr.renderapi.Entity.UserQuality;
import com.javaadr.renderapi.Repository.UserQualityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserQualityService {

    @Autowired
    private UserQualityRepository userQualityRepository;

    public UserQuality updateUserQuality(UUID userId, Integer qualityId, Double newLevel) {
        Optional<UserQuality> optionalUserQuality = userQualityRepository.findByUserIdAndQualityId(userId, qualityId);

        if (optionalUserQuality.isPresent()) {
            UserQuality userQuality = optionalUserQuality.get();
            Double currentLevel = userQuality.getLevel();
            Double updatedLevel = currentLevel + newLevel;
            userQuality.setLevel(updatedLevel);
            return userQualityRepository.save(userQuality);
        } else {
            return null;
        }
    }
}
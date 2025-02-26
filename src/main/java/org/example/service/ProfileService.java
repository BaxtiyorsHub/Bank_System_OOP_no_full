package org.example.service;

import org.example.dto.ProfileRequest;
import org.example.entity.ProfileEntity;
import org.example.enums.ProfileRole;
import org.example.enums.Status;
import org.example.exp.ProfileNotFoundException;
import org.example.repository.ProfileRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class ProfileService {
    private final ProfileRepository profileRepository = new ProfileRepository();

    public String register(ProfileRequest request) {
        //check
        ProfileEntity entity = new ProfileEntity();
        entity.setPassword(request.password());
        entity.setName(request.name());
        entity.setRole(ProfileRole.USER);
        entity.setBalance(0d);
        entity.setId(UUID.randomUUID());
        entity.setCreatedDate(LocalDateTime.now().toString());
        entity.setVisible(Boolean.TRUE);
        entity.setStatus(Status.ACTIVE);
        entity.setPhone(request.phone());
        profileRepository.save(List.of(entity));
        return "Success";
    }

    public ProfileEntity getProfile(UUID id) {
        return profileRepository.readData()
                .stream()
                .filter(profileEntity -> profileEntity.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found"));
    }

    public ProfileEntity login(String phone, String password) {
        return profileRepository.readData()
                .stream()
                .filter(profileEntity -> profileEntity.getPhone().equals(phone)
                        && profileEntity.getPassword().equals(password))
                .findFirst()
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found"));
    }

    public List<ProfileEntity> allProfiles() {
        return profileRepository.readData().stream()
                .filter(ProfileEntity::getVisible).toList();
    }
}


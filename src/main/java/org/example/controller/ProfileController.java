package org.example.controller;

import org.example.dto.ProfileRequest;
import org.example.entity.ProfileEntity;
import org.example.service.ProfileService;

import java.util.List;

public class ProfileController {
    private final ProfileService profileService = new ProfileService();

    public String register(ProfileRequest request) {
        return profileService.register(request);
    }

    public ProfileEntity login(String phone, String password) {
        return profileService.login(phone,password);
    }
    public List<ProfileEntity> allProfiles() {
        return profileService.allProfiles();
    }
}

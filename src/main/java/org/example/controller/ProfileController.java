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

    public boolean addCard(String card, ProfileEntity entity) {
        return profileService.addCard(card,entity);
    }

    public void changePassword(String cardNum, String password, ProfileEntity entity) {
        profileService.changePassword(cardNum,password,entity);
    }
}

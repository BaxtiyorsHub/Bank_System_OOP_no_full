package org.example.service;

import org.example.dto.ProfileRequest;
import org.example.entity.CardEntity;
import org.example.entity.ProfileEntity;
import org.example.entity.TransactionEntity;
import org.example.enums.ProfileRole;
import org.example.enums.Status;
import org.example.enums.Used;
import org.example.exp.ProfileNotFoundException;
import org.example.repository.ProfileRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProfileService {
    private final ProfileRepository profileRepository = new ProfileRepository();
    private final CardService cardService = new CardService();
    private final ProfileCardService profileCardService = new ProfileCardService();

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

    public boolean addCard(String cardNum, ProfileEntity profile) {
        CardEntity card = cardService.getCard(cardNum);
        if (card.getUsed().equals(Used.NOT_USED)) {
            cardService.statusChange(card);
            return profileCardService.profileAddCard(card, profile);
        } else {
            return false;
        }
    }

    public void changePassword(String cardNum, String password, ProfileEntity entity) {
        CardEntity card = cardService.getCard(cardNum);
        card.setPassword(password);
        cardService.changePassword(card);
        profileCardService.changePassword(entity,card);
    }

    public List<CardEntity> profileCards(ProfileEntity profile) {
        return profileCardService.profileCards(profile);
    }
}


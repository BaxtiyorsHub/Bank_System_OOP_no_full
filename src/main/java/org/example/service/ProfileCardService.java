package org.example.service;

import org.example.entity.CardEntity;
import org.example.entity.ProfileCardEntity;
import org.example.entity.ProfileEntity;
import org.example.entity.TransactionEntity;
import org.example.enums.Status;
import org.example.enums.Used;
import org.example.repository.CardRepository;
import org.example.repository.ProfileCardRepository;
import org.example.repository.TransactionRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProfileCardService {
    private final ProfileCardRepository profileCardRepository = new ProfileCardRepository();
    private final TransactionRepository transactionRepository = new TransactionRepository();

    public boolean profileAddCard(CardEntity card, ProfileEntity profile) {
        card.setStatus(Status.ACTIVE);
        card.setUsed(Used.USED);
        ProfileCardEntity profileCardEntity = new ProfileCardEntity();
        profileCardEntity.setCard(card);
        profileCardEntity.setProfile(profile);
        profileCardEntity.setId(UUID.randomUUID());
        profileCardRepository.save(List.of(profileCardEntity));
        return true;
    }

    public void changePassword(ProfileEntity entity, CardEntity card) {
        List<ProfileCardEntity> profileCardEntities = profileCardRepository.readData();
        profileCardEntities.stream()
                .filter(profileCardEntity -> profileCardEntity.getCard().getId().equals(card.getId())
                        && profileCardEntity.getProfile().getId().equals(entity.getId())).forEach(
                        profileCardEntity -> profileCardEntity.getCard().setPassword(card.getPassword()));
        profileCardRepository.clear();
        profileCardRepository.save(profileCardEntities);

    }

    public ProfileCardEntity getProfileCard(String cardNum) {
        return profileCardRepository.readData()
                .stream()
                .filter(t -> t.getCard().getCard().equals(cardNum))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Card is not found"));
    }

    public void changeBalance(ProfileCardEntity sender, ProfileCardEntity receiver, Double money) {
        List<ProfileCardEntity> list = profileCardRepository.readData().stream()
                .filter(p -> {
                    if (p.getCard().getCard().equals(sender.getCard().getCard())) {
                        p.getCard().setBalance(p.getCard().getBalance() - money*1.02);
                        return true;
                    } else if (p.getCard().getCard().equals(receiver.getCard().getCard())) {
                        p.getCard().setBalance(p.getCard().getBalance() + money);
                        return true;
                    }
                    return false;
                }).toList();
        profileCardRepository.clear();
        profileCardRepository.save(list);
    }

    public List<CardEntity> profileCards(ProfileEntity profile) {
        List<ProfileCardEntity> list = profileCardRepository.readData()
                .stream()
                .filter(pc -> pc.getProfile().getId().equals(profile.getId()))
                .toList();
        return list.stream().map(ProfileCardEntity::getCard).toList();
    }
}

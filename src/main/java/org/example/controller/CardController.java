package org.example.controller;

import org.example.dto.ProfileRequest;
import org.example.entity.CardEntity;
import org.example.service.CardService;
import org.example.service.ProfileService;

import java.util.List;
import java.util.UUID;

public class CardController {

    private final CardService cardService = new CardService();
    private final ProfileService profileService = new ProfileService();

    public String createCard(String card, String year) {
        return cardService.createCard(card,year);
    }

    public List<CardEntity> allCards() {
        return cardService.allCards();
    }

    public List<CardEntity> allNotActiveCards() {
        return cardService.notActiveCards();
    }

    public List<ProfileRequest.CardResponce> profileCards(UUID id) {

    }
}

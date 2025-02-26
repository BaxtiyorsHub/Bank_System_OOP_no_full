package org.example.service;

import org.example.entity.CardEntity;
import org.example.enums.Status;
import org.example.enums.Used;
import org.example.repository.CardRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardService {
    private final CardRepository cardRepository = new CardRepository();

    public String createCard(String card, String year) {
        CardEntity cardEntity = new CardEntity();
        cardEntity.setCard(card);
        cardEntity.setExpiredDate(year);
        cardRepository.save(List.of(cardEntity));
        return "Success";
    }

    public List<CardEntity> allCards() {
        return cardRepository.readData();
    }

    public List<CardEntity> notActiveCards() {
        return allCards().stream().filter(c -> c.getStatus().equals(Status.NOT_ACTIVE)).toList();

        /*List<CardEntity> cardEntities = allCards();
        List<CardEntity> notActiveCards = new ArrayList<>();
        for (CardEntity cardEntity : cardEntities) {
            if (cardEntity.getStatus() == Status.NOT_ACTIVE) {
                notActiveCards.add(cardEntity);
            }
        }
        return notActiveCards;*/
    }

    public CardEntity getCard(String cardNum) {
        return allCards()
                .stream()
                .filter(c -> c.getCard().equals(cardNum))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Card not found"));
    }

    public void statusChange(CardEntity card) {
        List<CardEntity> cardEntities = cardRepository.readData();
        cardEntities.stream()
                .filter(c -> c.getId().equals(card.getId()))
                .forEach(c -> {
                    c.setUsed(Used.USED);
                    c.setStatus(Status.ACTIVE);
                });
        cardRepository.clear();
        cardRepository.save(cardEntities);
    }

    public void changePassword(CardEntity card) {
        List<CardEntity> cardEntities = cardRepository.readData();
        cardEntities.stream()
                .filter(c -> c.getId().equals(card.getId()))
                .forEach(c -> {
                    c.setPassword(card.getPassword());
                });
        cardRepository.clear();
        cardRepository.save(cardEntities);
    }
}

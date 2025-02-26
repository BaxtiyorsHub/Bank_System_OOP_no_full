package org.example.service;

import org.example.entity.CardEntity;
import org.example.enums.Status;
import org.example.repository.CardRepository;

import java.util.ArrayList;
import java.util.List;

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
        List<CardEntity> cardEntities = cardRepository.readData();
        return cardEntities.stream().filter(c -> true).toList();
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
}

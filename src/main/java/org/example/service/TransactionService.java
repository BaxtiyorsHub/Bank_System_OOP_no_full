package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.TransactionEntity;
import org.example.repository.TransactionRepository;

import java.util.List;
import java.util.stream.Collectors;

public class TransactionService {
private final TransactionRepository transactionRepository = new TransactionRepository();

    public List<TransactionEntity> allTransaction() {
        return transactionRepository.readData();
    }

    public void makeTransaction(ProfileEntity entity, String cardNum, String receiverCardNum, Double money) {
        ProfileCardEntity sender = profileCardService.getProfileCard(cardNum);
        ProfileCardEntity receiver = profileCardService.getProfileCard(receiverCardNum);
        if (sender.getCard().getBalance() < money + money*0.02) {
            throw new RuntimeException("Not enough balance");
        }
        profileCardService.changeBalance(sender, receiver, money);
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setAmount(money);
        transactionEntity.setCommission(money*0.02);
        transactionEntity.setSenderCard(sender.getCard());
        transactionEntity.setReceiverCard(receiver.getCard());
        transactionEntity.setSenderProfile(entity);
        transactionEntity.setReceiverProfile(receiver.getProfile());

        transactionRepository.save(List.of(transactionEntity));
    }

    public List<TransactionEntity> profileTransaction(ProfileEntity profile) {
        return transactionRepository.readData()
                .stream()
                .filter(t -> t.getSenderProfile().equals(profile))
                .toList();
    }
}

package org.example.controller;

import org.example.entity.ProfileEntity;
import org.example.entity.TransactionEntity;
import org.example.service.TransactionService;

import java.util.List;

public class TransactionController {

    private final TransactionService transactionService = new TransactionService();

    public List<TransactionEntity> allTransaction() {
        return transactionService.allTransaction();
    }

    public void makeTransaction(ProfileEntity entity, String cardNum, String receiverCardNum, Double money) {
        transactionService.makeTransaction(entity, cardNum, receiverCardNum, money);
    }

    public List<TransactionEntity> myTransaction(ProfileEntity entity) {
        return transactionService.profileTransaction(entity);
    }
}

package org.example.controller;

import org.example.entity.TransactionEntity;
import org.example.service.TransactionService;

import java.util.List;

public class TransactionController {

    private final TransactionService transactionService = new TransactionService();

    public List<TransactionEntity> allTransaction() {
        return transactionService.allTransaction();
    }
}

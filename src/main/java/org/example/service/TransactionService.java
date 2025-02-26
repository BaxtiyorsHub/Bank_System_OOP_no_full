package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.TransactionEntity;
import org.example.repository.TransactionRepository;

import java.util.List;

public class TransactionService {
private final TransactionRepository transactionRepository = new TransactionRepository();

    public List<TransactionEntity> allTransaction() {
        return transactionRepository.readData();
    }
}

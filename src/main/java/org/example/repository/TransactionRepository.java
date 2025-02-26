package org.example.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.TransactionEntity;
import org.example.exp.TransactionFileNotFoundExp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {

    private final ObjectMapper transactionMapper = new ObjectMapper();
    private final File transactionFile = new File("transactions.json");

    public void save(List<TransactionEntity> transactions) {
        List<TransactionEntity> transactionEntities = readData();
        transactionEntities.addAll(transactions);
        try {
            transactionMapper.writeValue(transactionFile,transactionEntities);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public List<TransactionEntity> readData() {
        if (!transactionFile.exists() || transactionFile.length() == 0) return new ArrayList<>();
        try {
            List<TransactionEntity> transactionEntities = transactionMapper.readValue(transactionFile, new TypeReference<List<TransactionEntity>>() {
            });
            if (transactionEntities.isEmpty()) return new ArrayList<>();
            return transactionEntities;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

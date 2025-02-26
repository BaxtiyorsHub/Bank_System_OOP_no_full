package org.example.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.CardEntity;
import org.example.entity.ProfileEntity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CardRepository {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File file = new File("card.json");

    public void save(List<CardEntity> entity) {
        List<CardEntity> entities = readData();
        entities.addAll(entity);
        try {
            objectMapper.writeValue(file,entities);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CardEntity> readData() {
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }
        try {
            List<CardEntity> response = objectMapper.readValue(file, new TypeReference<>() {
            });
            if (response.isEmpty()) {
                return new ArrayList<>();
            }
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void clear(){
        try {
            objectMapper.writeValue(file,new ArrayList<CardEntity>());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

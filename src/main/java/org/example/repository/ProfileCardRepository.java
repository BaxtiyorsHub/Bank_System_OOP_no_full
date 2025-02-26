package org.example.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.ProfileCardEntity;
import org.example.entity.ProfileEntity;
import org.example.entity.TransactionEntity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProfileCardRepository {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File file = new File("profile_card.json");

    public void save(List<ProfileCardEntity> entity) {
        List<ProfileCardEntity> entities = readData();
        entities.addAll(entity);
        try {
            objectMapper.writeValue(file,entities);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ProfileCardEntity> readData() {
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }
        try {
            List<ProfileCardEntity> response = objectMapper.readValue(file, new TypeReference<>() {
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
            objectMapper.writeValue(file,new ArrayList<>());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package org.example.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.ProfileEntity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProfileRepository {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File file = new File("profile.json");

    public void save(List<ProfileEntity> entity) {
        List<ProfileEntity> entities = readData();
        entities.addAll(entity);
        try {
            objectMapper.writeValue(file,entities);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ProfileEntity> readData() {
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }
        try {
            List<ProfileEntity> response = objectMapper.readValue(file, new TypeReference<>() {
            });
            if (response.isEmpty()) {
                return new ArrayList<>();
            }
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfileCardEntity {
    private UUID id;
    private ProfileEntity profile;
    private CardEntity card;
    private String createdDate = LocalDateTime.now().toString();
}

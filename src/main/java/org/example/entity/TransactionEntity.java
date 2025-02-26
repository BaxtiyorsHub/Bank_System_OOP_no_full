package org.example.entity;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class TransactionEntity {
    private String transactionId;
    private String comment;
    private LocalDateTime date;
    private Boolean visible;
    private ProfileEntity profile;
    private CardEntity card;
}

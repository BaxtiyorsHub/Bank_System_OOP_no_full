package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionEntity {
    private CardEntity senderCard;
    private ProfileEntity senderProfile;
    private CardEntity receiverCard;
    private ProfileEntity receiverProfile;
    private String createdDate = LocalDateTime.now().toString();
    private Double amount;
    private Double commission;
}

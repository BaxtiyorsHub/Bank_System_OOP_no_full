package org.example.entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CardEntity extends BaseEntity{
    private String card;
    private String expiredDate;
    private String password;
}

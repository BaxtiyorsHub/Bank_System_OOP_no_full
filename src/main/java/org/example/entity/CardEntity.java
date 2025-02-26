package org.example.entity;

import lombok.*;
import org.example.enums.Used;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CardEntity extends BaseEntity{
    private String card;
    private String expiredDate;
    private String password;
    private Used used;
    private Double balance = 0d;
}

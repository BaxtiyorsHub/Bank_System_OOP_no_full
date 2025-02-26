package org.example.entity;

import lombok.*;
import org.example.enums.Status;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class BaseEntity {
    private UUID id = UUID.randomUUID();
    private Status status = Status.NOT_ACTIVE;
    private Boolean visible = Boolean.TRUE;
    private String createdDate = LocalDateTime.now().toString();
}

package org.example.entity;

import lombok.*;
import org.example.enums.ProfileRole;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProfileEntity extends BaseEntity {
    private String name;
    private String phone;
    private String password;
    private Double balance;
    private ProfileRole role;

    public ProfileEntity(String name, String phone, String password) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.balance = 0d;
        this.role = ProfileRole.USER;
    }
}

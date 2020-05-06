package com.falynsky.tss4.models.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersDTO {

    private int id;
    private String username;
    private String password;
    private String role;

}

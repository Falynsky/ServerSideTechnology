package com.falynsky.tss4.services;

import com.falynsky.tss4.models.DTO.UsersDTO;
import com.falynsky.tss4.models.Users;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UsersDTO retrieveBasicObject(Users users) {
        return new UsersDTO(users.getId(), users.getLogin(), users.getPassword());
    }
}

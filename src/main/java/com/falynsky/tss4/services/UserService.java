package com.falynsky.tss4.services;

import com.falynsky.tss4.modules.DTO.UsersDTO;
import com.falynsky.tss4.modules.Users;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UsersDTO retrieveBasicObject(Users users) {
        return new UsersDTO(users.getId(), users.getLogin(), users.getPassword());
    }
}

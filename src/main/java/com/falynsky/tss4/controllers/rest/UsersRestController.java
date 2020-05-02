package com.falynsky.tss4.controllers.rest;

import com.falynsky.tss4.modules.Users;
import com.falynsky.tss4.repositories.UsersRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/rest/users")
public class UsersRestController {

    final UsersRepository usersRepository;


    public UsersRestController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping("/all")
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }
}

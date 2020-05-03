package com.falynsky.tss4.controllers;

import com.falynsky.tss4.models.Users;
import com.falynsky.tss4.repositories.UsersRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UsersController {

    final UsersRepository usersRepository;


    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping("/all")
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }
}

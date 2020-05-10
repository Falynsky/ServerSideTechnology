package com.falynsky.tss4.controllers.rest;

import com.falynsky.tss4.models.DTO.UsersDTO;
import com.falynsky.tss4.services.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RestController
@RequestMapping("/rest/users")
public class UsersRestController {

    private final UserService userService;

    public UsersRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<UsersDTO> getAllUsersDTO() {
        return userService.retrieveBasicUsers();
    }
}

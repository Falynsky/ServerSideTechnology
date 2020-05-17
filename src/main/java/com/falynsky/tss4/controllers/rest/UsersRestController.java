package com.falynsky.tss4.controllers.rest;

import com.falynsky.tss4.models.DTO.UsersDTO;
import com.falynsky.tss4.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/users/DTO")
public class UsersRestController {

    private final UserService userService;

    public UsersRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UsersDTO>> getAllUsersDTO() {
        return userService.retrieveBasicUsers();
    }

    @GetMapping("/{username}")
    public ResponseEntity<UsersDTO> getUserDTObyUsername(@PathVariable String username) {
        return userService.retrieveBasicObjectByUsername(username);

    }
}

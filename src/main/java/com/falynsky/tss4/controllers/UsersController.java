package com.falynsky.tss4.controllers;

import com.falynsky.tss4.models.DTO.UsersDTO;
import com.falynsky.tss4.models.Users;
import com.falynsky.tss4.repositories.UsersRepository;
import com.falynsky.tss4.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@Controller
@RequestMapping("/user")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/welcome-page")
    public String welcomePage(Model model) {
        List<UsersDTO> users = userService.retrieveBasicUsers();
        model.addAttribute("users", users);
        return "welcome-page";
    }

    @GetMapping("/sign-up")
    public String singUp(Model model) {
        model.addAttribute("user", new Users());
        return "sign-up";
    }

    @PostMapping("/register")
    public String register(Users appUser) {
        userService.createAndAddUser(appUser);
        return "redirect:/grades";
    }

    @PostMapping("/login")
    public String login() {
        return "redirect:/grades";
    }
}

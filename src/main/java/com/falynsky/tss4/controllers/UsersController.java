package com.falynsky.tss4.controllers;

import com.falynsky.tss4.models.DTO.UsersDTO;
import com.falynsky.tss4.models.Users;
import com.falynsky.tss4.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/welcome-page")
    public String welcomePage(Model model) {
        return "welcome-page";
    }

    @GetMapping("/chat")
    public String chat() {
        return "chat";
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

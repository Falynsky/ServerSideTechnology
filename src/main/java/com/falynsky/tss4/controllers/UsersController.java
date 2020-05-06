package com.falynsky.tss4.controllers;

import com.falynsky.tss4.models.Users;
import com.falynsky.tss4.repositories.UsersRepository;
import com.falynsky.tss4.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@Controller
@RequestMapping("/user")
public class UsersController {

    final UsersRepository usersRepository;
    private UserService userService;

    public UsersController(UsersRepository usersRepository, UserService userService) {
        this.usersRepository = usersRepository;
        this.userService = userService;
    }

    @GetMapping("/sign-up")
    public String singUp(Model model) {
        model.addAttribute("user", new Users());
        return "sign-up";
    }

    @PostMapping("/register")
    public String register(Users appUser) {
        userService.createAndAddUser(appUser);
        return "redirect:/";
    }
}

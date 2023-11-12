package com.daniil.project.controller;

import com.daniil.project.entity.User;
import com.daniil.project.service.user.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class LoginController {

    private final UserService userService;


    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/showLoginPage")
    public String showLoginPage(){
        return "login";
    }

    @GetMapping("/registration")
    public String register(){
        return "register";
    }

    @PostMapping("/registration/save")
    public String registration(@ModelAttribute("user") User user){
        User existingUser = userService.findByUsername(user.getUsername());

        if(existingUser != null){
            return "redirect:/registration?error";
        }

        user.setPassword("{bcrypt}" + new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setEnabled(1);
        userService.save(user);
        return "redirect:/showLoginPage";
    }

    @GetMapping("/access-denied")
    public String accessDenied(){
        return "access-denied";
    }

}

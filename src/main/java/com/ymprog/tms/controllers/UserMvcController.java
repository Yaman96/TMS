package com.ymprog.tms.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ymprog.tms.entities.User;
import com.ymprog.tms.services.RoleService;
import com.ymprog.tms.services.UserService;

@Controller
@RequestMapping("/users")
public class UserMvcController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @GetMapping("/register")
    public String getRegistrationForm() {
        return "registration-form";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam("firstname") String firstName,
            @RequestParam("lastname") String lastName,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("email") String email,
            Model model) {

                if(userService.findByUsername(username.toLowerCase()) != null) {
                    model.addAttribute("error", "User with this username already exists.");
                    return "registration-form";
                }

                if(userService.findByEmail(email.toLowerCase()) != null) {
                    model.addAttribute("error", "User with this email already exists.");
                    return "registration-form";
                }

                User newUser = new User(username, encoder.encode(password), email, firstName, lastName, LocalDateTime.now(), false);
                
                userService.saveUserWithRole(newUser, roleService.findRoleByName("ROLE_USER"));

        return "login";
    }

}

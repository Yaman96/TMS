package com.ymprog.tms.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ymprog.tms.entities.User;
import com.ymprog.tms.services.PasswordResetService;
import com.ymprog.tms.services.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class PasswordResetController {

    @Autowired
    private PasswordResetService passwordResetService;

    @Autowired
    private UserService userService;

    @GetMapping("/password/reset")
    public String showPasswordResetForm() {
        return "password-reset-form";
    }

    @PostMapping("/password/reset")
    public String sendPasswordResetEmail(@RequestParam("email") String email, HttpServletRequest request, Model model) {
        User user = userService.findByEmail(email);

        if (user == null) {
            model.addAttribute("error", "User not found");
            return "password-reset-form";
        }

        String token = passwordResetService.getPasswordResetToken(user);

        String resetUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath() + "/reset-password?token=" + token;

        try {
            passwordResetService.sendPasswordResetEmail(email, resetUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }

        model.addAttribute("success", "Instructions to reset your password have been sent to your email.");

        return "password-reset-form";
    }
}

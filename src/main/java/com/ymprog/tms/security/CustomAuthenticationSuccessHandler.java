package com.ymprog.tms.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private TokenProvider tokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        String jwt = tokenProvider.generateToken(authentication);

        Cookie jwtCookie = new Cookie("JWT-TOKEN", jwt);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setMaxAge((int) tokenProvider.TOKEN_VALIDITY);
        jwtCookie.setPath("/"); // Устанавливаем путь cookie для всего сайта

        response.addCookie(jwtCookie);

        // Перенаправление на страницу, с которой пользователь был перенаправлен
        super.onAuthenticationSuccess(request, response, authentication);
    }
}

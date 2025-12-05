package com.example.APP.Company.infra.customError;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");


        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        String json;

        if (authException instanceof BadCredentialsException) {
            json = """
            {
                "error": "Incorrect username or password."
            }
            """;
        } else if (authException instanceof InsufficientAuthenticationException) {
            json = """
            {
                "error": "Authentication required."
            }
            """;
        } else {
            json = """
            {
                "error": "Invalid authentication token."
            }
            """;
        }

        response.getWriter().write(json);
    }
}

package com.project.authentication.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Configuration
public class UserAuthenticationEntry implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        response.setContentType(String.valueOf(MediaType.APPLICATION_JSON));
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        Map<String,Object> map = new HashMap<>();
        map.put("status",401);
        map.put("Error","unauthorized");
        map.put("message",authException.getMessage());
        map.put("path",request.getRequestURI());

        new ObjectMapper().writeValue(response.getOutputStream(),map);
    }
}

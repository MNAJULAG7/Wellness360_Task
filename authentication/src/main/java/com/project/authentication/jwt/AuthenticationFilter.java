package com.project.authentication.jwt;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtUtilities jwtUtilities;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(request.getServletPath().startsWith("/auth"))
        {
            filterChain.doFilter(request,response);
            return;
        }
        try
        {
            String token = request.getHeader("Authorization");
            token = token.length()>6? token.substring(7): null;
            if(token!=null && jwtUtilities.validateJwtToken(token))
            {
                String username = jwtUtilities.getUsernameFromToken(token);
                UserDetails user =  userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken u = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
                u.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(u);
            }
        } catch (Exception e) {
            System.out.println("Error occurred while authentication "+e.getMessage());
        }
        filterChain.doFilter(request,response);
    }
}

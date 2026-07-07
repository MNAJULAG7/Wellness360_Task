package com.project.authentication.controller;

import com.project.authentication.dto.LoginRequest;
import com.project.authentication.dto.SignupRequest;
import com.project.authentication.exception.UserDetailsExistException;
import com.project.authentication.exception.UserNotFoundException;
import com.project.authentication.jwt.JwtUtilities;
import com.project.authentication.model.User;
import com.project.authentication.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;


    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtilities jwtUtilities;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        Authentication authentication;

        try
        {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),request.getPassword()
                    )
            );
        }
        catch (Exception e){
            Map<String,Object> map = new HashMap<>();
            map.put("status",401);
            map.put("error","UnAunthenticated");
            map.put("message",e.getMessage());

            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtilities.generateToken(request.getUsername());

        User user = userRepo.findByUsername(request.getUsername()).orElseThrow(()-> new UserNotFoundException(request.getUsername()));
        Map<String,Object> map = new HashMap<>();
        map.put("unique_id",user.getId());
        map.put("username",request.getUsername());
        map.put("message","login successfully");

        return ResponseEntity.ok().header("Authorization",token).body(map);

    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request)
    {

        if(userRepo.existsByUsername(request.getUsername())) {
             throw new UserDetailsExistException("Username",request.getUsername());
           }


       else if(userRepo.existsByMail(request.getMail())){
            throw new UserDetailsExistException("Mail",request.getMail());
        }

       User user = new User();
       user.setName(request.getName());
       user.setUsername(request.getUsername());
       user.setMail(request.getMail());
       user.setMobile(request.getMobile());
       user.setPassword(passwordEncoder.encode(request.getPassword()));

       userRepo.save(user);
       return ResponseEntity.ok().body("Create your account sucessfully");

    }

}

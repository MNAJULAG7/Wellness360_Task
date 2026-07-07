package com.project.authentication.controller;

import com.project.authentication.dto.SignupRequest;
import com.project.authentication.dto.UpdateRequest;
import com.project.authentication.model.UserResonse;
import com.project.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResonse> getById(@PathVariable Long id)
    {
        return ResponseEntity.ok().body(userService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResonse> updateById(@PathVariable Long id, @RequestBody UpdateRequest request)
    {
        return ResponseEntity.ok().body(userService.updateById(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id)
    {
        userService.deleteById(id);
        return ResponseEntity.ok().body("deleted sucessfully");
    }
}

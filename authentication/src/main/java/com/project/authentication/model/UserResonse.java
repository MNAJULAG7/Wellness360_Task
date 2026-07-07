package com.project.authentication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResonse {
    private Long id;
    private String name;
    private String username;
    private String mail;
    private Long mobile;
}

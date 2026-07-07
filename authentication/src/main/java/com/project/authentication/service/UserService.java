package com.project.authentication.service;

import com.project.authentication.dto.UpdateRequest;
import com.project.authentication.model.UserResonse;
import org.springframework.http.ResponseEntity;

public interface UserService {
    UserResonse getById(Long id);

    UserResonse updateById(Long id, UpdateRequest request);

    void deleteById(Long id);
}
